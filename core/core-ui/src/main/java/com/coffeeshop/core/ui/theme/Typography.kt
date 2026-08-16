package com.coffeeshop.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.coffeeshop.core.ui.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Sora")

val SoraFontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.ExtraBold)
)

val Typography = Typography(

    displayLarge = TextStyle(
        fontFamily = SoraFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),

    headlineLarge = TextStyle(
        fontFamily = SoraFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),

    titleLarge = TextStyle(
        fontFamily = SoraFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),

    titleMedium = TextStyle(
        fontFamily = SoraFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = SoraFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = SoraFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    bodySmall = TextStyle(
        fontFamily = SoraFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    labelLarge = TextStyle(
        fontFamily = SoraFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),

    labelMedium = TextStyle(
        fontFamily = SoraFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
)
