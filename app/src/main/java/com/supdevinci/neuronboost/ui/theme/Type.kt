package com.supdevinci.neuronboost.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.supdevinci.neuronboost.R

val RobotoMedium = FontFamily(
    Font(R.font.robotomedium, FontWeight.Medium)
)

val DomineRegular = FontFamily(
    Font(R.font.domineregular)
)

val DomineBold = FontFamily(
    Font(R.font.dominebold)
)

val FiraSansLight = FontFamily(
    Font(R.font.firasanslight)
)

val Norwester = FontFamily(
    Font(R.font.norwester)
)


val buttonPlay = TextStyle(
    fontFamily = RobotoMedium,
    fontSize = 40.sp,
)

val buttonMenu = TextStyle(
    fontFamily = DomineRegular,
    fontSize = 28.sp,
)

val mainFont = TextStyle(
    fontFamily = DomineRegular,
    fontSize = 22.sp,
)

val labelQuestionNumber = TextStyle(
    fontFamily = FiraSansLight,
    fontSize = 16.sp,
)

val labelCategory = TextStyle(
    fontFamily = Norwester,
    fontSize = 24.sp,
)

val titleScore = TextStyle(
    fontFamily = DomineBold,
    fontSize = 36.sp,
)

val labelScore = TextStyle(
    fontFamily = DomineBold,
    fontSize = 72.sp,
)