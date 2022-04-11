package kieren.foenander.propertylist

/*
This class is to allow creation of a Property

A property is to have a unique identifier with the ID Variable
The address variable will be one variable which holds the street address and suburb
The price variable is for the sale price and will have no decimal places
the agent variable is for the agent that is managing the property
the property image variable is to set the name string for the property image

*/
data class Property(var id:String, var address:String, var price:Int, var agent: String, var propertyImage: String)