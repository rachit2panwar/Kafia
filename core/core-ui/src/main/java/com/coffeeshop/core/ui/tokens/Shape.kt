package com.coffeeshop.core.ui.tokens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val CoffeeShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),   // tags, small badges
    small      = RoundedCornerShape(8.dp),   // input fields, small cards
    medium     = RoundedCornerShape(12.dp),  // product cards, main cards
    large      = RoundedCornerShape(16.dp),  // bottom sheets, dialogs
    extraLarge = RoundedCornerShape(50.dp)   // buttons (pill), chips
)
