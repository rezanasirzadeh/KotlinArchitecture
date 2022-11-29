package ir.digireza.s1_koin.interfaces

import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module

val bmwModule = module {

    //factory<Car> { CarImpl() }
    //When have 1 interface
    //factory { CarImpl() } bind Car::class
    //When have more than  1 interface
    factory { CarImpl() } binds arrayOf(Car::class, Owner::class)


    factory { BMW(get()) }

}