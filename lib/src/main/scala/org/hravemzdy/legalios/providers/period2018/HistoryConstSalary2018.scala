package org.hravemzdy.legalios.providers.period2018

import org.hravemzdy.legalios.providers.period2017.HistoryConstSalary2017

// WORKING_SHIFT_WEEK      Počet pracovních dnů v týdnu
//
// WORKING_SHIFT_TIME      Počet pracovních hodin denně
//
// MIN_MONTHLY_WAGE        Minimální mzda měsíční
//
// MIN_HOURLY_WAGE         Minimální mzda hodinová (100*Kč)

object HistoryConstSalary2018 {
    val VERSION_CODE = 2018

    val WORKING_SHIFT_WEEK:Int = HistoryConstSalary2017.WORKING_SHIFT_WEEK
    val WORKING_SHIFT_TIME:Int = HistoryConstSalary2017.WORKING_SHIFT_TIME
    val MIN_MONTHLY_WAGE:Int = 12200
    val MIN_HOURLY_WAGE:Int = 7320
}

