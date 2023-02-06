package com.example.test1

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test1.ui.theme.MainTheme
import com.example.test1.ui.theme.PressedTheme

const val TAG = "myLog"

val textStyle1: TextStyle = TextStyle(
    fontWeight = FontWeight.Black,
    fontSize = 20.sp,
    letterSpacing = 0.5.sp
)

class ButtonTheme(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color,
    val textStyle: TextStyle
) {
    @Composable
    fun buttonColors(): ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor
    )
}

val PrimaryButtonTheme = ButtonTheme(
    backgroundColor = Color.White,
    contentColor = Color.Black,
    disabledBackgroundColor = Color.Magenta,
    disabledContentColor = Color.Red,
    textStyle = textStyle1
)

val PrimaryPressedButtonTheme = ButtonTheme(
    backgroundColor = Color.Cyan,
    contentColor = Color.Blue,
    disabledBackgroundColor = Color.Cyan,
    disabledContentColor = Color.Blue,
    textStyle = textStyle1
)

val outlinedBorderSize = 1.dp


@Composable
fun AddToCartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    forcePress: Boolean = false,
    loaderEnabled: Boolean = false,
    icon: ImageVector? = null,
    subtitle: String? = null,
    title: String,
) {

    AddToCartButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        forcePress = forcePress,
        loaderEnabled = loaderEnabled,
        icon = icon?.let {
            {
                Icon(
                    imageVector = icon,
                    contentDescription = "icon"
                )
            }
        },
        subtitle = subtitle?.let { { Text(text = subtitle) } },
        title = { Text(text = title) },
    )
}

@Composable
fun AddToCartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    forcePress: Boolean = false,
    loaderEnabled: Boolean = false,
    outlined: Boolean = false,
    icon: (@Composable () -> Unit)? = null,
    subtitle: (@Composable () -> Unit)? = null,
    title: (@Composable () -> Unit)? = null,
) {

   MainButton(
        modifier = modifier
            .width(200.dp)
            .height(52.dp),
        title = title,
        subtitle = subtitle,
        icon = icon,
        onClick = onClick,
        enabled = enabled,
        forcePress = forcePress,
        loaderEnabled = loaderEnabled,
    )

}

@Composable
fun TestButton2(
) {
    var isEnabled by remember { mutableStateOf(true) }
    var isForcePress by remember { mutableStateOf(false) }
    var isLoader by remember { mutableStateOf(false) }

    Column() {
        Button(onClick = { isEnabled = !isEnabled }) {
            Text(text = "enabled: $isEnabled")
        }
        Button(onClick = {
            isForcePress = !isForcePress
        }) {
            Text(text = "pressed: $isForcePress")
        }
        Button(onClick = { isLoader = !isLoader }) {
            Text(text = "loader: $isLoader")
        }

        AddToCartButton(
            onClick = {},
            enabled = isEnabled,
            forcePress = isForcePress,
            loaderEnabled = isLoader,
            title = "text text",
            subtitle = "sub",
            icon = Icons.Default.Home
        )

//        MainButton(
//            modifier = Modifier
//                .width(200.dp)
//                .height(52.dp),
//            title = {
//                Text(text = "Title")
//            },
//            subtitle = {
//                Text(text = "Subtitle")
//            },
//            icon = {
//                Icon(
//                    imageVector = Icons.Rounded.ShoppingCart,
//                    contentDescription = "icon"
//                )
//            },
//            onClick = {},
//            enabled = isEnabled,
//            forcePress = isForcePress,
//            loaderEnabled = isLoader,
//        )


//        val textStyle1 = TextStyle(
//            fontWeight = FontWeight.Black,
//            fontSize = 50.sp,
//            letterSpacing = 0.5.sp
//        )
    }
}


@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    forcePress: Boolean = false,
    loaderEnabled: Boolean = false,
    icon: (@Composable () -> Unit)? = null,
    subtitle: (@Composable () -> Unit)? = null,
    title: (@Composable () -> Unit)? = null,
) {
    BaseButton(
        modifier = modifier,
        enabled = enabled,
        forcePress = forcePress,
        onClick = onClick,
        loaderEnabled = loaderEnabled
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy((18.5).dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                icon()
            }
            if (title != null || subtitle != null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    title?.let {
                        title()
                    }
                    subtitle?.let {
                        subtitle()
                    }
                }
            }
        }
    }
}

@Composable
fun TestButton(
    title: String? = "title1",
    subtitle: String? = "subtitle2",
    imageVector: ImageVector? = Icons.Rounded.ShoppingCart,
    context: Context,
    enabled: Boolean = true,
    forcePress: Boolean = false,
    loaderAnimationEnabled: Boolean = false
) {
    var isEnabled by remember { mutableStateOf(enabled) }
    var isForcePress by remember { mutableStateOf(forcePress) }
    var isLoader by remember { mutableStateOf(loaderAnimationEnabled) }

//    val style: ButtonStyle = ContainedButtonStyle()
//
//    TextButtonStyle()

    Column() {

        MainTheme {
            Row() {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "1")
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    border = BorderStroke(outlinedBorderSize, LocalContentColor.current)
                ) {
                    Text(text = "2")
                }
                Button(onClick = { /*TODO*/ }, enabled = false) {
                    Text(text = "1")
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    enabled = false,
                    border = BorderStroke(outlinedBorderSize, LocalContentColor.current)
                ) {
                    Text(text = "2")
                }
            }
        }
        PressedTheme() {
            Row() {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "1")
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    border = BorderStroke(outlinedBorderSize, LocalContentColor.current)
                ) {
                    Text(text = "2")
                }
                Button(onClick = { /*TODO*/ }, enabled = false) {
                    Text(text = "1")
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    enabled = false,
                    border = BorderStroke(outlinedBorderSize, LocalContentColor.current)
                ) {
                    Text(text = "2")
                }
            }
        }
        Button(onClick = { isEnabled = !isEnabled }) {
            Text(text = "enabled: $isEnabled")
        }
        Button(onClick = {
            isForcePress = !isForcePress
        }) {
            Text(text = "pressed: $isForcePress")
        }
        Button(onClick = { isLoader = !isLoader }) {
            Text(text = "loader: $isLoader")
        }

        val textStyle1 = TextStyle(
            fontWeight = FontWeight.Black,
            fontSize = 50.sp,
            letterSpacing = 0.5.sp
        )

        BaseButton(
            Modifier.width(200.dp),
            enabled = isEnabled,
            forcePress = isForcePress,
            onClick = { },
            loaderEnabled = isLoader
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (imageVector != null) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "icon"
                    )
                }
                Column() {
                    if (!title.isNullOrBlank()) {
                        Text(text = "title")
                    }
                    if (!subtitle.isNullOrBlank()) {
                        Text(text = subtitle)
                    }
                }
            }
        }
    }
}


@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    loaderEnabled: Boolean = false,
    forcePress: Boolean = false,
//    buttonColors: ButtonColors = PrimaryButtonTheme.buttonColors(),
    pressedButtonColors: ButtonColors = PrimaryPressedButtonTheme.buttonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
//    val currentButtonColors =
//        if (isPressed || forcePress) pressedButtonColors else buttonColors

    MainTheme(pressed = isPressed || forcePress) {

//            val col = MaterialTheme.colors.
        Button(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = interactionSource,
//            colors = currentButtonColors,
            content = if (loaderEnabled) {
                { DotsFlashing() }
            } else {
                {
                    ProvideTextStyle(
//                            value = LocalTextStyle.current.merge(PrimaryButtonTheme.textStyle),
                        value = LocalTextStyle.current.merge(
                            TextStyle(
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp,
                                letterSpacing = 0.5.sp
                            )
                        ),
                        content = content
                    )
                }
            }
        )
    }
}

@Composable
fun OutlinedBaseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    loaderAnimationEnabled: Boolean = false,
    forcePress: Boolean = false,
//    buttonColors: ButtonColors = PrimaryButtonTheme.buttonColors(),
    pressedButtonColors: ButtonColors = PrimaryPressedButtonTheme.buttonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
//    val currentButtonColors =
//        if (isPressed || forcePress) pressedButtonColors else buttonColors

    MainTheme(pressed = isPressed || forcePress) {

        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = interactionSource,
            border = if (enabled) BorderStroke(
                outlinedBorderSize,
                LocalContentColor.current
            ) else ButtonDefaults.outlinedBorder,
//            colors = currentButtonColors,
            content = if (loaderAnimationEnabled) {
                { DotsFlashing() }
            } else {
                {
                    ProvideTextStyle(
                        value = LocalTextStyle.current.merge(PrimaryButtonTheme.textStyle),
                        content = content
                    )
                }
            }
        )
    }
}

@Composable
fun DotsFlashing(
    color: Color = LocalContentColor.current,
    dotSize: Dp = 8.dp,
    spaceSize: Dp = 8.dp,
    delayUnit: Int = 300,
    minAlpha: Float = 0.0f,
    shape: Shape = CircleShape,
) {

    @Composable
    fun Dot(
        alpha: Float
    ) = Spacer(
        Modifier
            .size(dotSize)
            .alpha(alpha)
            .background(
                color = color, shape = shape
            )
    )

    val infiniteTransition = rememberInfiniteTransition()

    @Composable
    fun animateAlphaWithDelay(delay: Int) = infiniteTransition.animateFloat(
        initialValue = minAlpha,
        targetValue = minAlpha,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = delayUnit * 4
                minAlpha at delay with LinearEasing
                1f at delay + delayUnit with LinearEasing
                minAlpha at delay + delayUnit * 2
            }
        )
    )

    val alpha1 by animateAlphaWithDelay(0)
    val alpha2 by animateAlphaWithDelay(delayUnit)
    val alpha3 by animateAlphaWithDelay(delayUnit * 2)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spaceSize)
    ) {
        Dot(alpha1)
        Dot(alpha2)
        Dot(alpha3)
    }
}

@Preview(showBackground = true)
@Composable
fun MainButtonPreview() {
    DotsFlashing()
//    Test1Theme {
//        BaseButton()
//    }
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme by remember { mutableStateOf(false) }
//            Test1Theme(darkTheme) {
            Column(Modifier.padding(16.dp)) {
                TestButton2()
                TestButton(context = applicationContext)
            }
//            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Test1Theme {
//        Greeting("Android")
//    }
//}