package org.example.project.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

sealed class Screen {
    object Splash : Screen()
    object Onboarding : Screen()
    object Login : Screen()
    object CarType : Screen()
    object Subscription : Screen()
    object Payment : Screen()
    object Success : Screen()
    object Dashboard : Screen()
    object Schedule : Screen()
    object Manage : Screen()
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(BlueDark, BluePrimary))),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(
                modifier = Modifier.size(80.dp),
                color = Color.White.copy(alpha = 0.2f),
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(2.dp, Color.White.copy(alpha = 0.4f))
            ) {
                Icon(
                    imageVector = Icons.Default.DirectionsCar,
                    contentDescription = null,
                    modifier = Modifier.padding(16.dp).size(48.dp),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "WashPro",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Premium Car Wash At Your Doorstep",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            LinearProgressIndicator(
                modifier = Modifier.width(120.dp).height(4.dp),
                color = Color.White,
                trackColor = Color.White.copy(alpha = 0.2f)
            )
        }
    }
}

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pages = listOf(
        OnboardingPage("Book Car Wash Easily", "Choose your package and schedule your wash in seconds.", Icons.Default.CalendarMonth),
        OnboardingPage("Flexible Subscription Plans", "Monthly packages for every car owner. Affordable and premium.", Icons.Default.Star),
        OnboardingPage("Track & Manage Everything", "Manage cars, plans, and wash schedules anytime, anywhere.", Icons.Default.Settings)
    )

    var currentPage by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f).background(Brush.linearGradient(listOf(Color(0xFFEFF6FF), Color(0xFFDBEAFE)))),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = pages[currentPage].icon, contentDescription = null, modifier = Modifier.size(100.dp), tint = BluePrimary)
        }

        Column(modifier = Modifier.fillMaxWidth().padding(28.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = pages[currentPage].title, style = MaterialTheme.typography.headlineLarge, color = TextDark, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = pages[currentPage].description, style = MaterialTheme.typography.bodyLarge, color = TextGray, textAlign = TextAlign.Center, lineHeight = 20.sp)
            
            Row(modifier = Modifier.padding(vertical = 32.dp), horizontalArrangement = Arrangement.Center) {
                repeat(pages.size) { index ->
                    val width by animateDpAsState(if (index == currentPage) 24.dp else 8.dp)
                    Box(modifier = Modifier.padding(4.dp).width(width).height(8.dp).background(if (index == currentPage) BluePrimary else Color(0xFFCBD5E1), RoundedCornerShape(4.dp)))
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onFinish, modifier = Modifier.weight(1f).height(50.dp), shape = RoundedCornerShape(14.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, BluePrimary)) {
                    Text("Skip", fontWeight = FontWeight.SemiBold, color = BluePrimary)
                }
                Button(onClick = { if (currentPage < pages.size - 1) currentPage++ else onFinish() }, modifier = Modifier.weight(1f).height(50.dp), shape = RoundedCornerShape(14.dp), colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)) {
                    Text(if (currentPage == pages.size - 1) "Get Started" else "Next", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Box(modifier = Modifier.fillMaxWidth().height(180.dp).background(Brush.linearGradient(listOf(BlueDark, BluePrimary)), RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)).padding(20.dp), contentAlignment = Alignment.BottomStart) {
            Column {
                Text(text = "Welcome back 👋", style = MaterialTheme.typography.labelMedium, color = Color.White.copy(alpha = 0.7f))
                Text(text = "Sign in to WashPro", style = MaterialTheme.typography.headlineLarge, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Column(modifier = Modifier.fillMaxWidth().padding(20.dp).verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(10.dp))
            Text("Email", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            OutlinedTextField(value = email, onValueChange = { email = it }, placeholder = { Text("you@example.com") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            Spacer(modifier = Modifier.height(14.dp))
            Text("Password", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            OutlinedTextField(value = password, onValueChange = { password = it }, placeholder = { Text("••••••••") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation())
            Spacer(modifier = Modifier.height(14.dp))
            Text("Phone Number", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            OutlinedTextField(value = phone, onValueChange = { phone = it }, placeholder = { Text("+971 50 000 0000") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onLogin, modifier = Modifier.fillMaxWidth().height(54.dp), shape = RoundedCornerShape(14.dp), colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)) {
                Text("Login", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE2E8F0))
                Text(" or continue with ", style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(horizontal = 10.dp))
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE2E8F0))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(onClick = { }, modifier = Modifier.weight(1f).height(50.dp), shape = RoundedCornerShape(14.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
                    Text("🌐 Google", color = TextDark)
                }
                OutlinedButton(onClick = { }, modifier = Modifier.weight(1f).height(50.dp), shape = RoundedCornerShape(14.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
                    Text("🍎 Apple", color = TextDark)
                }
            }
        }
    }
}

@Composable
fun CarTypeScreen(onContinue: () -> Unit, onBack: () -> Unit) {
    val cars = listOf(CarOption("Sedan", "🚗"), CarOption("SUV", "🚙"), CarOption("Hatchback", "🚘"), CarOption("Luxury", "🏎️"), CarOption("Pickup", "🛻"))
    var selectedCar by remember { mutableStateOf("Sedan") }

    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight)) {
        Row(modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(32.dp).clickable { onBack() }, color = Color(0xFFF1F5F9), shape = RoundedCornerShape(10.dp)) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.padding(6.dp), tint = TextDark)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text("Choose Your Car Type", style = MaterialTheme.typography.titleLarge, color = TextDark)
        }

        Column(modifier = Modifier.weight(1f).padding(16.dp).verticalScroll(rememberScrollState())) {
            FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 2, horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                cars.forEach { car ->
                    val isSelected = selectedCar == car.label
                    Surface(modifier = Modifier.weight(1f).height(110.dp).clickable { selectedCar = car.label }, color = if (isSelected) Color(0xFFEFF6FF) else Color.White, shape = RoundedCornerShape(18.dp), border = androidx.compose.foundation.BorderStroke(2.dp, if (isSelected) BluePrimary else Color(0xFFE2E8F0))) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Text(car.icon, fontSize = 36.sp)
                            Text(car.label, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = TextDark)
                        }
                    }
                }
            }
        }

        Surface(modifier = Modifier.fillMaxWidth(), color = Color.White, shadowElevation = 8.dp) {
            Button(onClick = onContinue, modifier = Modifier.padding(20.dp).fillMaxWidth().height(54.dp), shape = RoundedCornerShape(14.dp), colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)) {
                Text("Continue", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun SubscriptionScreen(onSubscribe: () -> Unit, onBack: () -> Unit) {
    val plans = listOf(
        PlanOption("Normal Plan", "100", listOf("4 washes per month", "Basic exterior cleaning")),
        PlanOption("Regular Plan", "180", listOf("8 washes per month", "Interior + exterior cleaning", "Priority scheduling"), isPopular = true),
        PlanOption("Premium Plan", "240", listOf("Unlimited priority washes", "Wax & polish included", "VIP service"), isPremium = true)
    )
    var selectedPlan by remember { mutableStateOf("Regular Plan") }

    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight)) {
        Row(modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(32.dp).clickable { onBack() }, color = Color(0xFFF1F5F9), shape = RoundedCornerShape(10.dp)) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.padding(6.dp), tint = TextDark)
            }
            Text("Choose Subscription", style = MaterialTheme.typography.titleLarge, color = TextDark, modifier = Modifier.padding(start = 12.dp))
        }

        Column(modifier = Modifier.weight(1f).padding(16.dp).verticalScroll(rememberScrollState())) {
            plans.forEach { plan ->
                val isSelected = selectedPlan == plan.name
                Box(modifier = Modifier.padding(bottom = 12.dp)) {
                    Surface(modifier = Modifier.fillMaxWidth().clickable { selectedPlan = plan.name }, color = if (plan.isPremium) BlueDark else Color.White, shape = RoundedCornerShape(18.dp), border = androidx.compose.foundation.BorderStroke(2.dp, if (isSelected || plan.isPopular) BluePrimary else Color(0xFFE2E8F0))) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(plan.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = if (plan.isPremium) Color.White else TextDark)
                                Column(horizontalAlignment = Alignment.End) {
                                    Text("AED ${plan.price}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = if (plan.isPremium) Color.White else BluePrimary)
                                    Text("/mo", style = MaterialTheme.typography.labelSmall, color = if (plan.isPremium) Color.White.copy(0.7f) else TextGray)
                                }
                            }
                            plan.features.forEach { Row(verticalAlignment = Alignment.CenterVertically) { Icon(Icons.Default.Check, null, modifier = Modifier.size(14.dp), tint = if (plan.isPremium) Color(0xFF86EFAC) else SuccessGreen); Text(it, style = MaterialTheme.typography.bodySmall, color = if (plan.isPremium) Color.White.copy(0.85f) else TextGray, modifier = Modifier.padding(start = 6.dp)) } }
                        }
                    }
                    if (plan.isPopular || plan.isPremium) Surface(modifier = Modifier.align(Alignment.TopEnd).offset(x = (-16).dp, y = (-10).dp), color = if (plan.isPremium) Color.White else BluePrimary, shape = RoundedCornerShape(20.dp)) { Text(if (plan.isPremium) "👑 Premium" else "⭐ Most Popular", color = if (plan.isPremium) BluePrimary else Color.White, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)) }
                }
            }
        }
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.White, shadowElevation = 8.dp) {
            Button(onClick = onSubscribe, modifier = Modifier.padding(20.dp).fillMaxWidth().height(54.dp), shape = RoundedCornerShape(14.dp), colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)) {
                Text("Subscribe Now", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DashboardScreen(onLogout: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
                NavigationBarItem(icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home", fontSize = 9.sp) }, selected = selectedTab == 0, onClick = { selectedTab = 0 }, colors = NavigationBarItemDefaults.colors(selectedIconColor = BluePrimary, selectedTextColor = BluePrimary, unselectedIconColor = Color(0xFFCBD5E1), unselectedTextColor = Color(0xFFCBD5E1), indicatorColor = Color.Transparent))
                NavigationBarItem(icon = { Icon(Icons.Default.CalendarToday, null) }, label = { Text("Schedule", fontSize = 9.sp) }, selected = selectedTab == 1, onClick = { selectedTab = 1 }, colors = NavigationBarItemDefaults.colors(selectedIconColor = BluePrimary, selectedTextColor = BluePrimary, unselectedIconColor = Color(0xFFCBD5E1), unselectedTextColor = Color(0xFFCBD5E1), indicatorColor = Color.Transparent))
                NavigationBarItem(icon = { Icon(Icons.Default.Star, null) }, label = { Text("Plans", fontSize = 9.sp) }, selected = selectedTab == 2, onClick = { selectedTab = 2 }, colors = NavigationBarItemDefaults.colors(selectedIconColor = BluePrimary, selectedTextColor = BluePrimary, unselectedIconColor = Color(0xFFCBD5E1), unselectedTextColor = Color(0xFFCBD5E1), indicatorColor = Color.Transparent))
                NavigationBarItem(icon = { Icon(Icons.Default.Person, null) }, label = { Text("Profile", fontSize = 9.sp) }, selected = selectedTab == 3, onClick = { selectedTab = 3 }, colors = NavigationBarItemDefaults.colors(selectedIconColor = BluePrimary, selectedTextColor = BluePrimary, unselectedIconColor = Color(0xFFCBD5E1), unselectedTextColor = Color(0xFFCBD5E1), indicatorColor = Color.Transparent))
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> HomeContent(onSchedule = { selectedTab = 1 }, onManage = { selectedTab = 2 })
                1 -> ScheduleScreen(onBack = { selectedTab = 0 }, onConfirm = { selectedTab = 0 })
                2 -> ManageSubscriptionScreen(onBack = { selectedTab = 0 }, onUpgrade = { selectedTab = 2 })
                3 -> ProfileContent(onLogout = onLogout)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeContent(onSchedule: () -> Unit, onManage: () -> Unit) {
    val user = MockUser
    val washes = MockUpcomingWashes
    
    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight).verticalScroll(rememberScrollState())) {
        Row(modifier = Modifier.fillMaxWidth().background(Color.White).padding(14.dp, 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column { Text("Good morning 👋", style = MaterialTheme.typography.labelMedium); Text(user.name, style = MaterialTheme.typography.titleLarge, color = TextDark) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(modifier = Modifier.size(36.dp), color = Color(0xFFF1F5F9), shape = RoundedCornerShape(12.dp)) { Icon(Icons.Default.Notifications, null, modifier = Modifier.padding(8.dp), tint = Color(0xFF374151)) }
                Spacer(modifier = Modifier.width(8.dp))
                Surface(modifier = Modifier.size(36.dp), color = BluePrimary, shape = RoundedCornerShape(12.dp)) { Box(contentAlignment = Alignment.Center) { Text(user.avatar, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp) } }
            }
        }

        Box(modifier = Modifier.padding(12.dp).fillMaxWidth().height(90.dp).background(Brush.linearGradient(listOf(BlueDark, BluePrimary)), RoundedCornerShape(18.dp)).padding(18.dp)) {
            Column { Text("Your next wash", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.7f)); Text("Tomorrow at 10:00 AM", style = MaterialTheme.typography.titleMedium, color = Color.White, fontWeight = FontWeight.Bold); Text("Exterior + Interior • Your Sedan", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.65f)) }
        }

        Surface(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), color = Color.White, shape = RoundedCornerShape(18.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column { Surface(color = Color(0xFFDBEAFE), shape = RoundedCornerShape(20.dp)) { Text("Regular Plan", color = BluePrimary, fontWeight = FontWeight.Bold, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)) }; Spacer(modifier = Modifier.height(4.dp)); Row(verticalAlignment = Alignment.CenterVertically) { Box(modifier = Modifier.size(8.dp).background(SuccessGreen, CircleShape)); Text("Active until Jun 16", style = MaterialTheme.typography.labelSmall, color = TextGray, modifier = Modifier.padding(start = 4.dp)) } }
                    Column(horizontalAlignment = Alignment.End) { Text("5", style = MaterialTheme.typography.headlineMedium, color = BluePrimary, fontWeight = FontWeight.Bold); Text("washes left", style = MaterialTheme.typography.labelSmall, color = TextGray) }
                }
                Spacer(modifier = Modifier.height(10.dp))
                LinearProgressIndicator(progress = { 0.625f }, modifier = Modifier.fillMaxWidth().height(6.dp), color = BluePrimary, trackColor = Color(0xFFE2E8F0), strokeCap = androidx.compose.ui.graphics.StrokeCap.Round)
                Row(modifier = Modifier.fillMaxWidth().padding(top = 5.dp), horizontalArrangement = Arrangement.SpaceBetween) { Text("5 of 8 remaining", style = MaterialTheme.typography.labelSmall, color = TextGray); Text("AED 180/mo", style = MaterialTheme.typography.labelSmall, color = TextGray) }
            }
        }

        FlowRow(modifier = Modifier.padding(12.dp).fillMaxWidth(), maxItemsInEachRow = 2, horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            MockQuickActions.forEach { item ->
                Surface(modifier = Modifier.weight(1f).clickable { when(item.label) { "Schedule Wash" -> onSchedule(); "Subscription" -> onManage() } }, color = Color.White, shape = RoundedCornerShape(14.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
                    Column(modifier = Modifier.padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) { Surface(modifier = Modifier.size(40.dp), color = item.bgColor, shape = RoundedCornerShape(12.dp)) { Icon(item.icon, null, modifier = Modifier.padding(8.dp), tint = item.iconColor) }; Spacer(modifier = Modifier.height(6.dp)); Text(item.label, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TextDark) }
                }
            }
        }

        Column(modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 20.dp)) {
            Text("Upcoming Washes", style = MaterialTheme.typography.titleSmall, color = TextDark)
            Spacer(modifier = Modifier.height(10.dp))
            washes.forEach { wash ->
                Surface(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), color = Color.White, shape = RoundedCornerShape(18.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
                    Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Surface(modifier = Modifier.size(42.dp), color = BluePrimary, shape = RoundedCornerShape(10.dp)) { Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) { Text(wash.day, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp); Text(wash.month, color = Color.White.copy(0.8f), fontSize = 9.sp) } }
                        Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) { Text(wash.type, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = TextDark); Text("${wash.time} · ${wash.location}", style = MaterialTheme.typography.labelSmall, color = TextGray) }
                        Surface(color = Color(0xFFDCFCE7), shape = RoundedCornerShape(20.dp)) { Text(wash.status, color = wash.statusColor, fontWeight = FontWeight.SemiBold, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)) }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileContent(onLogout: () -> Unit) {
    val user = MockUser
    Column(modifier = Modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Surface(modifier = Modifier.size(100.dp), color = BluePrimary, shape = CircleShape) { Box(contentAlignment = Alignment.Center) { Text(user.avatar, color = Color.White, style = MaterialTheme.typography.headlineLarge) } }
        Spacer(modifier = Modifier.height(20.dp))
        Text(user.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(user.email, color = TextGray)
        Text(user.phone, color = TextGray)
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onLogout, modifier = Modifier.fillMaxWidth().height(50.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEE2E2), contentColor = ErrorRed)) {
            Text("Logout", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PaymentScreen(onPay: () -> Unit, onBack: () -> Unit) {
    var selectedCard by remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight)) {
        Row(modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(32.dp).clickable { onBack() }, color = Color(0xFFF1F5F9), shape = RoundedCornerShape(10.dp)) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null, modifier = Modifier.padding(6.dp), tint = TextDark) }
            Text("Payment", style = MaterialTheme.typography.titleLarge, color = TextDark, modifier = Modifier.padding(start = 12.dp))
        }

        Column(modifier = Modifier.weight(1f).padding(16.dp).verticalScroll(rememberScrollState())) {
            Text("Payment Method", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = TextGray)
            @OptIn(ExperimentalLayoutApi::class)
            FlowRow(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), maxItemsInEachRow = 2, horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                val methods = listOf("Credit Card" to Icons.Default.CreditCard, "Apple Pay" to Icons.Default.PhoneIphone, "Cash" to Icons.Default.Payments, "Google Pay" to Icons.Default.AccountBalanceWallet)
                methods.forEachIndexed { index, (label, icon) -> val isSelected = selectedCard == index; Surface(modifier = Modifier.weight(1f).clickable { selectedCard = index }, color = if (isSelected) Color(0xFFEFF6FF) else Color.White, shape = RoundedCornerShape(14.dp), border = androidx.compose.foundation.BorderStroke(2.dp, if (isSelected) BluePrimary else Color(0xFFE2E8F0))) { Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) { Icon(icon, null, modifier = Modifier.size(24.dp), tint = if (isSelected) BluePrimary else TextDark); Spacer(modifier = Modifier.height(6.dp)); Text(label, fontSize = 11.sp, fontWeight = FontWeight.SemiBold) } } }
            }

            Box(modifier = Modifier.padding(top = 20.dp).fillMaxWidth().height(100.dp).background(Brush.linearGradient(listOf(BlueDark, BluePrimary)), RoundedCornerShape(14.dp)).padding(16.dp)) {
                Column { Text("4242 •••• •••• 8910", color = Color.White, fontWeight = FontWeight.Bold, letterSpacing = 2.sp); Spacer(modifier = Modifier.height(12.dp)); Row { Column { Text("Expires", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.7f)); Text("08/27", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp) }; Spacer(modifier = Modifier.width(24.dp)); Column { Text("CVV", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.7f)); Text("•••", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp) } } }
            }

            Surface(modifier = Modifier.padding(top = 16.dp).fillMaxWidth(), color = Color.White, shape = RoundedCornerShape(16.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Card Number", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold); OutlinedTextField(value = "", onValueChange = {}, placeholder = { Text("1234 5678 9012 3456", fontSize = 12.sp) }, modifier = Modifier.fillMaxWidth().height(48.dp), shape = RoundedCornerShape(10.dp))
                    Row(modifier = Modifier.padding(top = 10.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Column(modifier = Modifier.weight(1f)) { Text("Expiry", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold); OutlinedTextField(value = "", onValueChange = {}, placeholder = { Text("MM/YY", fontSize = 12.sp) }, modifier = Modifier.fillMaxWidth().height(48.dp), shape = RoundedCornerShape(10.dp)) }
                        Column(modifier = Modifier.weight(1f)) { Text("CVV", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold); OutlinedTextField(value = "", onValueChange = {}, placeholder = { Text("•••", fontSize = 12.sp) }, modifier = Modifier.fillMaxWidth().height(48.dp), shape = RoundedCornerShape(10.dp)) }
                    }
                }
            }

            Surface(modifier = Modifier.padding(top = 16.dp).fillMaxWidth(), color = Color(0xFFF8FAFC), shape = RoundedCornerShape(14.dp)) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("Order Summary", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) { Text("Regular Plan", fontSize = 12.sp, color = TextGray); Text("AED 180.00", fontSize = 12.sp, fontWeight = FontWeight.SemiBold) }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("VAT (5%)", fontSize = 12.sp, color = TextGray); Text("AED 9.00", fontSize = 12.sp, fontWeight = FontWeight.SemiBold) }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFFE2E8F0))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("Total", fontWeight = FontWeight.Bold, fontSize = 14.sp); Text("AED 189.00", color = BluePrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp) }
                }
            }
        }

        Surface(modifier = Modifier.fillMaxWidth(), color = Color.White, shadowElevation = 8.dp) { Button(onClick = onPay, modifier = Modifier.padding(20.dp).fillMaxWidth().height(54.dp), shape = RoundedCornerShape(14.dp), colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)) { Text("Pay Now – AED 189", fontWeight = FontWeight.Bold, fontSize = 16.sp) } }
    }
}

@Composable
fun SuccessScreen(onDashboard: () -> Unit, onSchedule: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Surface(modifier = Modifier.size(100.dp), color = SuccessGreen, shape = CircleShape) { Icon(Icons.Default.Check, null, modifier = Modifier.padding(24.dp), tint = Color.White) }
        Text("Subscription Activated!", style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 24.dp))
        Text("Your Regular Plan is now active.\nEnjoy 8 premium washes this month.", style = MaterialTheme.typography.bodyLarge, color = TextGray, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 8.dp))
        Surface(modifier = Modifier.padding(top = 30.dp).fillMaxWidth(), color = BackgroundLight, shape = RoundedCornerShape(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                listOf("Plan" to "Regular Plan", "Car" to "Sedan", "Washes" to "8/month", "Expires" to "Jun 16, 2026", "Total Paid" to "AED 189.00").forEach { (label, value) ->
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.SpaceBetween) { Text(label, color = TextGray, fontSize = 12.sp); Text(value, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = if (label == "Total Paid") BluePrimary else TextDark) }
                    if (label != "Total Paid") HorizontalDivider(color = Color(0xFFF1F5F9))
                }
            }
        }
        Button(onClick = onDashboard, modifier = Modifier.padding(top = 32.dp).fillMaxWidth().height(54.dp), shape = RoundedCornerShape(14.dp), colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)) { Text("Go to Dashboard", fontWeight = FontWeight.Bold) }
        OutlinedButton(onClick = onSchedule, modifier = Modifier.padding(top = 12.dp).fillMaxWidth().height(54.dp), shape = RoundedCornerShape(14.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, BluePrimary)) { Text("Schedule First Wash", color = BluePrimary, fontWeight = FontWeight.Bold) }
    }
}

@Composable
fun ScheduleScreen(onBack: () -> Unit, onConfirm: () -> Unit) {
    var selectedTime by remember { mutableStateOf("10:00 AM") }
    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight)) {
        Row(modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(32.dp).clickable { onBack() }, color = Color(0xFFF1F5F9), shape = RoundedCornerShape(10.dp)) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null, modifier = Modifier.padding(6.dp), tint = TextDark) }
            Text("Schedule Wash", style = MaterialTheme.typography.titleLarge, color = TextDark, modifier = Modifier.padding(start = 12.dp))
        }

        Column(modifier = Modifier.weight(1f).padding(12.dp).verticalScroll(rememberScrollState())) {
            Surface(modifier = Modifier.fillMaxWidth(), color = Color.White, shape = RoundedCornerShape(18.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Icon(Icons.Default.ChevronLeft, null); Text("May 2026", fontWeight = FontWeight.Bold); Icon(Icons.Default.ChevronRight, null) }
                    @OptIn(ExperimentalLayoutApi::class)
                    FlowRow(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), maxItemsInEachRow = 7) { (1..31).forEach { day -> Box(modifier = Modifier.weight(1f).aspectRatio(1f).padding(2.dp).background(if (day == 17) BluePrimary else Color.Transparent, RoundedCornerShape(8.dp)).clickable { }, contentAlignment = Alignment.Center) { Text(day.toString(), fontSize = 11.sp, color = if (day == 17) Color.White else TextDark) } } }
                }
            }

            Surface(modifier = Modifier.padding(top = 12.dp).fillMaxWidth(), color = Color.White, shape = RoundedCornerShape(18.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("Available Time Slots", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                    @OptIn(ExperimentalLayoutApi::class)
                    FlowRow(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), maxItemsInEachRow = 3, horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) { listOf("8:00 AM", "10:00 AM", "12:00 PM", "2:00 PM", "4:00 PM", "6:00 PM").forEach { time -> val isSelected = selectedTime == time; Surface(modifier = Modifier.weight(1f).clickable { selectedTime = time }, color = if (isSelected) BluePrimary else Color.White, shape = RoundedCornerShape(10.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, if (isSelected) BluePrimary else Color(0xFFE2E8F0))) { Text(time, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = if (isSelected) Color.White else TextDark) } } }
                }
            }

            Surface(modifier = Modifier.padding(top = 12.dp).fillMaxWidth(), color = Color.White, shape = RoundedCornerShape(18.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
                Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(40.dp), color = Color(0xFFEFF6FF), shape = RoundedCornerShape(12.dp)) { Icon(Icons.Default.LocationOn, null, modifier = Modifier.padding(10.dp), tint = BluePrimary) }
                    Column(modifier = Modifier.weight(1f).padding(start = 10.dp)) { Text("Home Parking", fontWeight = FontWeight.Bold, fontSize = 12.sp); Text("Al Rashidiya, Ajman", color = TextGray, fontSize = 11.sp) }
                    Text("Change", color = BluePrimary, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                }
            }
        }
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.White, shadowElevation = 8.dp) { Button(onClick = onConfirm, modifier = Modifier.padding(20.dp).fillMaxWidth().height(54.dp), shape = RoundedCornerShape(14.dp), colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)) { Text("Confirm Schedule", fontWeight = FontWeight.Bold) } }
    }
}

@Composable
fun ManageSubscriptionScreen(onBack: () -> Unit, onUpgrade: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight)) {
        Row(modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(32.dp).clickable { onBack() }, color = Color(0xFFF1F5F9), shape = RoundedCornerShape(10.dp)) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null, modifier = Modifier.padding(6.dp), tint = TextDark) }
            Text("Manage Subscription", style = MaterialTheme.typography.titleLarge, color = TextDark, modifier = Modifier.padding(start = 12.dp))
        }

        Column(modifier = Modifier.padding(12.dp).verticalScroll(rememberScrollState())) {
            Surface(modifier = Modifier.fillMaxWidth(), color = Color.White, shape = RoundedCornerShape(18.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
                Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(44.dp), color = BlueDark, shape = RoundedCornerShape(14.dp)) { Icon(Icons.Default.DirectionsCar, null, modifier = Modifier.padding(10.dp), tint = Color.White) }
                    Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) { Text("Regular Plan", fontWeight = FontWeight.Bold, fontSize = 14.sp); Row(verticalAlignment = Alignment.CenterVertically) { Box(modifier = Modifier.size(6.dp).background(SuccessGreen, CircleShape)); Text("Active · Renews Jun 16", color = TextGray, fontSize = 11.sp, modifier = Modifier.padding(start = 4.dp)) } }
                    Text("AED 180", color = BluePrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            val options = listOf(
                ManageOption("Upgrade Subscription", "Move to Premium Plan", Icons.AutoMirrored.Filled.TrendingUp, Color(0xFFDBEAFE), BluePrimary),
                ManageOption("Downgrade Subscription", "Move to Normal Plan", Icons.AutoMirrored.Filled.TrendingDown, Color(0xFFFEF3C7), WarningOrange),
                ManageOption("Pause Subscription", "Temporarily pause your plan", Icons.Default.Pause, Color(0xFFF3E8FF), Color(0xFF7C3AED)),
                ManageOption("Change Car", "Update vehicle details", Icons.Default.DirectionsCar, Color(0xFFDCFCE7), SuccessGreen)
            )

            options.forEach { option ->
                Surface(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).clickable { if (option.title.contains("Upgrade")) onUpgrade() }, color = Color.White, shape = RoundedCornerShape(14.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0))) {
                    Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Surface(modifier = Modifier.size(40.dp), color = option.bgColor, shape = RoundedCornerShape(12.dp)) { Icon(option.icon, null, modifier = Modifier.padding(10.dp), tint = option.iconColor) }
                        Column(modifier = Modifier.weight(1f).padding(start = 14.dp)) { Text(option.title, fontWeight = FontWeight.Bold, fontSize = 13.sp); Text(option.description, color = TextGray, fontSize = 11.sp) }
                        Icon(Icons.Default.ChevronRight, null, tint = Color(0xFFCBD5E1))
                    }
                }
            }

            Surface(modifier = Modifier.fillMaxWidth().padding(top = 4.dp).clickable { }, color = Color.White, shape = RoundedCornerShape(14.dp), border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFFEE2E2))) {
                Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(40.dp), color = Color(0xFFFEE2E2), shape = RoundedCornerShape(12.dp)) { Icon(Icons.Default.Delete, null, modifier = Modifier.padding(10.dp), tint = ErrorRed) }
                    Column(modifier = Modifier.weight(1f).padding(start = 14.dp)) { Text("Cancel Subscription", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ErrorRed); Text("End your current plan", color = TextGray, fontSize = 11.sp) }
                    Icon(Icons.Default.ChevronRight, null, tint = Color(0xFFCBD5E1))
                }
            }
        }
    }
}

data class OnboardingPage(val title: String, val description: String, val icon: ImageVector)
data class CarOption(val label: String, val icon: String)
data class PlanOption(val name: String, val price: String, val features: List<String>, val isPopular: Boolean = false, val isPremium: Boolean = false)
data class QuickActionItem(val label: String, val icon: ImageVector, val bgColor: Color, val iconColor: Color)
data class ManageOption(val title: String, val description: String, val icon: ImageVector, val bgColor: Color, val iconColor: Color)
