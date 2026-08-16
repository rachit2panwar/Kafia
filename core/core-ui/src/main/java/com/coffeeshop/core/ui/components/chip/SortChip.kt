package com.coffeeshop.core.ui.components.chip

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SortChip(
    label: String,
    sortOrder: SortOrder,
    currentSort: SortOrder,
    onClick: (SortOrder) -> Unit,
    modifier: Modifier = Modifier
) {
    CategoryChip(
        label = label,
        isSelected = sortOrder == currentSort,
        onClick = { onClick(sortOrder) },
        modifier = modifier
    )
}

enum class SortOrder { DEFAULT, PRICE_ASC, PRICE_DESC, POPULAR }
