package com.coffeeshop.core.ui.tokens

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// 4dp grid system — use ONLY these values for all padding and spacing
data class Spacing(
    val xxs:  Dp =  2.dp,
    val xs:   Dp =  4.dp,
    val sm:   Dp =  8.dp,
    val md:   Dp = 12.dp,
    val lg:   Dp = 16.dp,   // default screen horizontal padding
    val xl:   Dp = 24.dp,
    val xxl:  Dp = 32.dp,
    val xxxl: Dp = 48.dp,
    val huge:  Dp = 64.dp
)
