import java.util.*

@Suppress("UNCHECKED_CAST")
class Tour {
    private var tour = ArrayList<City?>()
    private var distance = 0

    constructor() {
        for (i in TourManager.destinationCities) {
            tour.add(null)
        }
    }

    constructor(tour: ArrayList<City?>) {
        this.tour = tour.clone() as ArrayList<City?>
    }

    fun getTour(): ArrayList<City?> = tour

    fun generateIndividual() {
        for (i in TourManager.destinationCities.indices) {
            setCity(i, TourManager.getCity(i))
        }

        tour.shuffle()
    }

    fun setCity(tourPosition: Int, city: City) {
        tour[tourPosition] = city
        distance = 0
    }

    fun getCity(tourPosition: Int): City? = tour[tourPosition]

    fun getDistance(): Int {
        if (distance == 0) {
            var tourDistance = 0

            for (i in tour.indices) {
                val fromCity: City? = getCity(i)
                val destinationCity: City? = if (i + 1 < tour.size) getCity(i + 1) else getCity(0)

                tourDistance += destinationCity?.let { fromCity?.distanceTo(it)?.toInt() }!!
            }

            distance = tourDistance
        }

        return distance
    }

    override fun toString(): String {
        var generateString = "|"

        for(i in tour.indices){
            generateString += "${getCity(i)} |"
        }
        return generateString
    }
}