class City(private val x: Int = (Math.random() * 200).toInt(),
           private val y: Int = (Math.random() * 200).toInt()) {

    override fun toString(): String = "$x, $y "

    fun distanceTo(city: City): Double {
        val xDistance: Int = Math.abs(x - city.x)
        val yDistance: Int = Math.abs(y - city.y)
        return Math.sqrt(((xDistance * xDistance) + (yDistance * yDistance)).toDouble())
    }

}