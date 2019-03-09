class TourManager {
    companion object {
        val destinationCities = ArrayList<City>()
        fun addCity(city: City) {
            destinationCities.add(city)
        }

        fun getCity(index: Int): City = destinationCities[index]
    }
}