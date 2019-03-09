class Runner{
    companion object {
        fun acceptanceProbability(energy: Int, newEnergy: Int, temp: Double) : Double{
            return if(newEnergy < energy) 1.0 else Math.exp((energy - newEnergy) / temp)
        }
    }
}

fun main(args: Array<String>){

    val semarang = City(120, 80)
    TourManager.addCity(semarang)
    val bandung = City(60, 20)
    TourManager.addCity(bandung)
    val jakarta = City(20,80)
    TourManager.addCity(jakarta)
    val malang = City(180, 10)
    TourManager.addCity(malang)
    val tasik = City(80, 30)
    TourManager.addCity(tasik)
    val cirebon = City(65, 90)
    TourManager.addCity(cirebon)
    val surabaya = City(200, 30)
    TourManager.addCity(surabaya)

    var temp = 10000.0
    val coolingRate = 0.003

    var currentSolution = Tour()
    currentSolution.generateIndividual()

    println("Initial solution distance: ${currentSolution.getDistance()}")

    var best = Tour(currentSolution.getTour())

    while (temp > 1){
        val newSolution = Tour(currentSolution.getTour())

        val tourPos1: Int = ((newSolution.getTour().size * Math.random()).toInt())
        val tourPos2: Int = (((newSolution.getTour().size * Math.random()).toInt()))

        val citySwap1 = newSolution.getCity(tourPos1)
        val citySwap2 = newSolution.getCity(tourPos2)

        citySwap1?.let { newSolution.setCity(tourPos2, it) }
        citySwap2?.let { newSolution.setCity(tourPos1, it) }

        val currentEnergy = currentSolution.getDistance()
        val neighborsEnergy = newSolution.getDistance()

        if(Runner.acceptanceProbability(currentEnergy, neighborsEnergy, temp) > Math.random()){
            currentSolution = Tour(newSolution.getTour())
        }

        if(currentSolution.getDistance() < best.getDistance()){
            best = Tour(currentSolution.getTour())
        }

        temp *= 1 - coolingRate
    }

    println("Final solution distance: " + best.getDistance())
    print("Tour: $best")
}