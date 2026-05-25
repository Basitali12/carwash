package org.example.project.ui

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.ui.components.*
import org.example.project.getPlatform
import kotlinx.coroutines.delay

// --- NAVIGATION ---
sealed class Screen {
    object Splash : Screen()
    object Onboarding : Screen()
    object PhoneEntry : Screen()
    object SignUp : Screen()
    object OtpVerify : Screen()
    object RoleSelection : Screen()
    object Dashboard : Screen()
    data class StationDetail(val station: Station) : Screen()
    object ExploreStations : Screen()
    data class Booking(val station: Station) : Screen()
    data class Payment(val station: Station, val service: WashService, val date: String, val time: String) : Screen()
    object PartnerLogin : Screen()
    object StationRegisterStep1 : Screen()
    object StationRegisterStep2 : Screen()
    object StationRegisterStep3 : Screen()
    object OwnerDashboard : Screen()
    object AdminPanel : Screen()
    object BookingDetail : Screen()
    object Analytics : Screen()
    object Reviews : Screen()
}

// --- SCREENS ---

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) { delay(2000); onTimeout() }
    Box(modifier = Modifier.fillMaxSize().background(BrandPrimary), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(modifier = Modifier.size(88.dp), color = Color.White, shape = RoundedCornerShape(22.dp)) { Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.WaterDrop, null, modifier = Modifier.size(46.dp), tint = BrandPrimary) } }
            Spacer(modifier = Modifier.height(22.dp)); Text("SparkleWash", style = MaterialTheme.typography.headlineLarge, color = Color.White, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif); Text("A cleaner ride, on demand", style = MaterialTheme.typography.bodyLarge, color = Color.White.copy(alpha = 0.85f))
        }
    }
}

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(BackgroundColor).padding(24.dp)) {
        Text("Skip", modifier = Modifier.align(Alignment.End).clickable { onFinish() }, fontSize = 11.sp, color = TextTertiary); Spacer(modifier = Modifier.height(18.dp)); Box(modifier = Modifier.fillMaxWidth().height(200.dp).background(BrandLight, RoundedCornerShape(14.dp)), contentAlignment = Alignment.Center) { Icon(Icons.Default.DirectionsCar, null, modifier = Modifier.size(56.dp), tint = BrandPrimary) }
        Spacer(modifier = Modifier.height(24.dp)); Column(horizontalAlignment = Alignment.CenterHorizontally) { Text("Book a wash in seconds", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary); Text("Find nearby stations, compare prices, and reserve your slot instantly.", fontSize = 11.sp, color = TextSecondary, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 6.dp, start = 8.dp, end = 8.dp)) }
        Row(modifier = Modifier.padding(top = 24.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) { Box(modifier = Modifier.size(18.dp, 7.dp).background(BrandPrimary, RoundedCornerShape(4.dp))); Spacer(modifier = Modifier.width(5.dp)); Box(modifier = Modifier.size(7.dp).background(Color.Black.copy(alpha = 0.14f), CircleShape)); Spacer(modifier = Modifier.width(5.dp)); Box(modifier = Modifier.size(7.dp).background(Color.Black.copy(alpha = 0.14f), CircleShape)) }
        Spacer(modifier = Modifier.weight(1f)); Button(onClick = onFinish, modifier = Modifier.fillMaxWidth().height(48.dp), shape = RoundedCornerShape(11.dp), colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary)) { Text("Continue", fontWeight = FontWeight.Bold) }
    }
}

@Composable
fun RoleSelectionScreen(onRoleSelected: (UserRole) -> Unit, onBack: () -> Unit) {
    var selectedRole by remember { mutableStateOf(UserRole.CUSTOMER) }
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).padding(24.dp).verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(32.dp)); Row(verticalAlignment = Alignment.CenterVertically) { Surface(modifier = Modifier.size(40.dp), color = SuccessGreen, shape = RoundedCornerShape(8.dp)) { Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.WaterDrop, null, modifier = Modifier.size(20.dp), tint = Color.White) } }; Text(text = "SparkleWash", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 12.dp), color = TextPrimary) }
        Spacer(modifier = Modifier.height(40.dp)); Text(text = buildAnnotatedString { append("How will you \nuse "); withStyle(style = SpanStyle(color = SuccessGreen, fontStyle = FontStyle.Italic)) { append("SparkleWash?") } }, style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif), color = TextPrimary); Text(text = "Choose your account type to get started. You can always switch later.", fontSize = 15.sp, color = TextSecondary, modifier = Modifier.padding(top = 12.dp, bottom = 40.dp))
        AccountTypeCard(title = "I'm a customer", description = "Book a car wash near me, anytime.", icon = Icons.Default.DirectionsCar, isSelected = selectedRole == UserRole.CUSTOMER, onClick = { selectedRole = UserRole.CUSTOMER }); Spacer(modifier = Modifier.height(16.dp)); AccountTypeCard(title = "I own a wash station", description = "List my station, manage bookings, grow my business.", icon = Icons.Default.Store, isSelected = selectedRole == UserRole.STATION_OWNER, onClick = { selectedRole = UserRole.STATION_OWNER })
        Spacer(modifier = Modifier.weight(1f)); Spacer(modifier = Modifier.height(40.dp)); Button(onClick = { onRoleSelected(selectedRole) }, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary)) { Row(verticalAlignment = Alignment.CenterVertically) { Text("Continue", fontWeight = FontWeight.Bold, fontSize = 16.sp); Spacer(modifier = Modifier.width(8.dp)); Icon(Icons.AutoMirrored.Filled.ArrowForward, null, modifier = Modifier.size(20.dp)) } }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 24.dp), horizontalArrangement = Arrangement.Center) { Text("Already have an account? ", fontSize = 14.sp, color = TextSecondary); Text(text = "Sign in", fontSize = 14.sp, color = SuccessGreen, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { onBack() }) }
    }
}

// --- CUSTOMER FLOW ---

@Composable
fun PhoneEntryScreen(onSendOtp: (String) -> Unit, onSignUp: () -> Unit, onBack: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isAndroid = remember { getPlatform().name.contains("Android", ignoreCase = true) }
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).padding(24.dp).verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(32.dp)); Text("Welcome back", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, color = TextPrimary); Text("Sign in to continue", fontSize = 14.sp, color = TextSecondary, modifier = Modifier.padding(top = 8.dp, bottom = 32.dp))
        AuthTextField(value = phone, onValueChange = { phone = it }, label = "Phone number", placeholder = "+971 50 123 4567"); Spacer(modifier = Modifier.height(16.dp)); AuthTextField(value = password, onValueChange = { password = it }, label = "Password", placeholder = "••••••••", isPasswordField = true)
        Text("Forgot password?", fontSize = 12.sp, color = BrandPrimary, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.End).padding(top = 12.dp).clickable { }); PrimaryAuthButton(text = "Sign in", onClick = { onSendOtp(phone) }, modifier = Modifier.padding(top = 32.dp))
        Text("or continue with", fontSize = 14.sp, color = TextTertiary, modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp), textAlign = TextAlign.Center)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) { if (!isAndroid) SocialAuthButton("Apple", Icons.Default.Smartphone, onClick = {}, modifier = Modifier.weight(1f)); SocialAuthButton("Google", Icons.Default.Language, onClick = {}, modifier = Modifier.weight(1f)) }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 32.dp), horizontalArrangement = Arrangement.Center) { Text("New here? ", fontSize = 14.sp, color = TextPrimary); Text("Sign up", fontSize = 14.sp, color = BrandPrimary, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { onSignUp() }) }
    }
}

@Composable
fun SignUpScreen(onSignUpComplete: (String) -> Unit, onSignIn: () -> Unit, onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isAndroid = remember { getPlatform().name.contains("Android", ignoreCase = true) }
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).padding(24.dp).verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(32.dp)); Text("Create Account", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, color = TextPrimary); Text("Join us for a cleaner ride", fontSize = 14.sp, color = TextSecondary, modifier = Modifier.padding(top = 8.dp, bottom = 32.dp))
        AuthTextField(value = name, onValueChange = { name = it }, label = "Full Name", placeholder = "John Doe"); Spacer(modifier = Modifier.height(16.dp)); AuthTextField(value = phone, onValueChange = { phone = it }, label = "Phone number", placeholder = "+971 50 123 4567"); Spacer(modifier = Modifier.height(16.dp)); AuthTextField(value = password, onValueChange = { password = it }, label = "Password", placeholder = "••••••••", isPasswordField = true)
        PrimaryAuthButton(text = "Sign up", onClick = { onSignUpComplete(phone) }, modifier = Modifier.padding(top = 32.dp)); Text("or continue with", fontSize = 14.sp, color = TextTertiary, modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp), textAlign = TextAlign.Center)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) { if (!isAndroid) SocialAuthButton("Apple", Icons.Default.Smartphone, onClick = {}, modifier = Modifier.weight(1f)); SocialAuthButton("Google", Icons.Default.Language, onClick = {}, modifier = Modifier.weight(1f)) }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 32.dp), horizontalArrangement = Arrangement.Center) { Text("Already have an account? ", fontSize = 14.sp, color = TextPrimary); Text("Sign in", fontSize = 14.sp, color = BrandPrimary, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { onSignIn() }) }
    }
}

@Composable
fun OtpVerifyScreen(phone: String, onVerify: () -> Unit, onBack: () -> Unit) {
    var otp by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().background(BackgroundColor).padding(24.dp)) {
        IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }
        Column(modifier = Modifier.fillMaxWidth().weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("Verification Code", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif)
            Text("Enter the code sent to $phone", fontSize = 11.sp, color = TextSecondary)
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(value = otp, onValueChange = { if(it.length <= 4) otp = it }, placeholder = { Text("0 0 0 0") }, modifier = Modifier.width(180.dp), shape = RoundedCornerShape(12.dp), textStyle = MaterialTheme.typography.headlineLarge.copy(textAlign = TextAlign.Center, letterSpacing = 8.sp), colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = SurfaceColor2, focusedContainerColor = SurfaceColor2, unfocusedBorderColor = Color.Transparent, focusedBorderColor = BrandPrimary))
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onVerify, modifier = Modifier.fillMaxWidth().height(48.dp), shape = RoundedCornerShape(11.dp), colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary)) { Text("Verify & Continue", fontWeight = FontWeight.Bold) }
            TextButton(onClick = onBack) { Text("Edit Phone Number", color = BrandPrimary, fontSize = 12.sp) }
        }
    }
}

@Composable
fun DashboardScreen(onLogout: () -> Unit, onAdmin: () -> Unit, onStationDetail: (Station) -> Unit, onSeeAll: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = { NavigationBar(containerColor = SurfaceColor, tonalElevation = 0.dp, modifier = Modifier.height(72.dp)) { listOf("Home" to Icons.Default.Home, "Books" to Icons.Default.CalendarToday, "Map" to Icons.Default.Map, "Me" to Icons.Default.Person).forEachIndexed { index, (label, icon) -> NavigationBarItem(icon = { Icon(icon, null, modifier = Modifier.size(22.dp)) }, label = { Text(label, fontSize = 9.sp, fontWeight = FontWeight.Medium) }, selected = selectedTab == index, onClick = { selectedTab = index }, colors = NavigationBarItemDefaults.colors(selectedIconColor = BrandPrimary, selectedTextColor = BrandPrimary, unselectedIconColor = TextTertiary, unselectedTextColor = TextTertiary, indicatorColor = Color.Transparent)) } } }
    ) { paddingValues -> Box(modifier = Modifier.padding(paddingValues)) { when (selectedTab) { 0 -> HomeContent(onDetail = onStationDetail, onSeeAll = onSeeAll); 1 -> BookingsListScreen(onDetail = {}); 2 -> MapContent(); 3 -> ProfileContent(onLogout = onLogout, onAdmin = onAdmin) } } }
}

@Composable
fun HomeContent(onDetail: (Station) -> Unit, onSeeAll: () -> Unit) {
    val scrollState = rememberScrollState()
    var searchQuery by remember { mutableStateOf("") }
    val verifiedStations = remember(searchQuery) { MockStations.filter { it.status == VerificationStatus.VERIFIED && (searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true)) } }
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).verticalScroll(scrollState)) {
        Surface(modifier = Modifier.padding(20.dp).fillMaxWidth(), color = BrandPrimary, shape = RoundedCornerShape(20.dp)) { Column(modifier = Modifier.padding(24.dp)) { Text(text = "Good morning", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f)); Text(text = "Ahmed Khalifa", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(vertical = 4.dp)); Row(verticalAlignment = Alignment.CenterVertically) { Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.White); Spacer(modifier = Modifier.width(4.dp)); Text(text = "Al Reem Island", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f)) } } }
        SearchField(value = searchQuery, onValueChange = { searchQuery = it }, placeholder = "Find a wash station")
        Text(text = "Services", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) { ServiceItem("Quick", Icons.Default.DirectionsCar, modifier = Modifier.weight(1f)) { searchQuery = "Quick" }; ServiceItem("Full", Icons.Default.LocalDrink, modifier = Modifier.weight(1f)) { searchQuery = "Full" }; ServiceItem("Detail", Icons.Default.AutoAwesome, modifier = Modifier.weight(1f)) { searchQuery = "Detail" }; ServiceItem("Mobile", Icons.Default.Home, modifier = Modifier.weight(1f)) { searchQuery = "Mobile" } }
        Row(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) { Text(text = "Nearby", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary); Text(text = "See all", fontSize = 14.sp, color = BrandPrimary, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { onSeeAll() }) }
        Column(modifier = Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) { if (verifiedStations.isEmpty()) { Box(modifier = Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) { Text("No stations found", color = TextSecondary) } } else { verifiedStations.take(2).forEach { station -> StationRow(station) { onDetail(station) } } }; Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
fun ExploreStationsScreen(onBack: () -> Unit, onDetail: (Station) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Open now") }
    val filters = listOf("Open now", "Top rated", "Cheapest")
    val filteredStations = remember(searchQuery, selectedFilter) { MockStations.filter { it.status == VerificationStatus.VERIFIED && (searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true)) } }
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor)) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) { SearchField(value = searchQuery, onValueChange = { searchQuery = it }, placeholder = "Crystal Clean"); Spacer(modifier = Modifier.height(16.dp)); Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) { filters.forEach { filter -> FilterChip(label = filter, isSelected = selectedFilter == filter, onClick = { selectedFilter = filter }, modifier = Modifier.weight(1f)) } } }
        Box(modifier = Modifier.fillMaxWidth().height(240.dp).background(BrandLight)) { Canvas(modifier = Modifier.fillMaxSize()) { val gridStep = 60.dp.toPx(); var x = 0f; while (x < size.width) { drawLine(Color.White.copy(alpha = 0.5f), start = androidx.compose.ui.geometry.Offset(x, 0f), end = androidx.compose.ui.geometry.Offset(x, size.height)); x += gridStep }; var y = 0f; while (y < size.height) { drawLine(Color.White.copy(alpha = 0.5f), start = androidx.compose.ui.geometry.Offset(0f, y), end = androidx.compose.ui.geometry.Offset(size.width, y)); y += gridStep } }; MapMarker(Modifier.align(Alignment.Center).offset(x = (-60).dp, y = (-40).dp), color = SuccessGreen); MapMarker(Modifier.align(Alignment.Center), color = Color(0xFF185FA5), isLarge = true); MapMarker(Modifier.align(Alignment.Center).offset(x = 80.dp, y = 30.dp), color = BrandPrimary); Surface(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp).size(44.dp), color = Color.White, shape = RoundedCornerShape(10.dp), shadowElevation = 4.dp) { Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.MyLocation, null, modifier = Modifier.size(20.dp), tint = TextSecondary) } } }
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp).verticalScroll(rememberScrollState())) { Text(text = "${filteredStations.size} stations near you", fontSize = 15.sp, color = TextPrimary, modifier = Modifier.padding(vertical = 20.dp)); filteredStations.forEach { station -> ExploreStationCard(station = station, isSelected = station.name.contains("Crystal Clean"), onClick = { onDetail(station) }); Spacer(modifier = Modifier.height(12.dp)) }; Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
fun StationDetailScreen(station: Station, onBack: () -> Unit, onBook: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor)) {
        Box(modifier = Modifier.fillMaxWidth().height(280.dp).background(BrandPrimary)) { Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Icon(Icons.Default.Image, null, modifier = Modifier.size(80.dp), tint = Color.White.copy(alpha = 0.3f)) }; Surface(modifier = Modifier.padding(16.dp).size(40.dp).clickable { onBack() }, color = Color.White, shape = CircleShape) { Box(contentAlignment = Alignment.Center) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null, modifier = Modifier.size(20.dp), tint = TextPrimary) } }; Surface(modifier = Modifier.align(Alignment.TopEnd).padding(16.dp).size(40.dp).clickable { }, color = Color.White, shape = CircleShape) { Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.FavoriteBorder, null, modifier = Modifier.size(20.dp), tint = TextPrimary) } }; Surface(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp), color = Color.Black.copy(alpha = 0.3f), shape = RoundedCornerShape(20.dp)) { Text(text = "1/6", color = Color.White, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) } }
        Column(modifier = Modifier.fillMaxSize().padding(20.dp).verticalScroll(scrollState)) { Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) { Text(text = station.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = TextPrimary); Surface(color = SuccessGreen.copy(alpha = 0.05f), shape = RoundedCornerShape(8.dp)) { Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) { Icon(Icons.Default.Star, null, modifier = Modifier.size(14.dp), tint = Color(0xFFBA7517)); Spacer(modifier = Modifier.width(4.dp)); Text(text = station.rating, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = SuccessGreen) } } }; Text(text = "${station.location} · ${station.distance}", fontSize = 14.sp, color = TextSecondary, modifier = Modifier.padding(top = 4.dp)); Row(modifier = Modifier.padding(top = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) { DetailBadge(Icons.Default.AccessTime, "25 min"); DetailBadge(Icons.Default.Payments, "35+"); StatusBadge("Open") }; Text(text = "Services", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)); station.services.forEach { service -> ServiceDetailCard(service) }; Spacer(modifier = Modifier.height(24.dp)); PrimaryAuthButton(text = "Book a service", onClick = onBook); Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
fun BookingScreen(station: Station, onBack: () -> Unit, onContinue: (WashService, String, String) -> Unit) {
    var selectedService by remember { mutableStateOf(station.services.firstOrNull()) }
    var selectedDate by remember { mutableStateOf("TUE 25") }
    var selectedTime by remember { mutableStateOf("11:30") }
    val dates = listOf("MON 24", "TUE 25", "WED 26", "THU 27")
    val times = listOf("10:00", "11:30", "13:00", "15:30")
    Scaffold(
        topBar = { Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }; Text("Book a wash", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) } },
        bottomBar = { Surface(modifier = Modifier.fillMaxWidth(), tonalElevation = 8.dp, shadowElevation = 8.dp, color = SurfaceColor) { Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) { Column { Text("Total", fontSize = 12.sp, color = TextSecondary); Text(text = "AED ${selectedService?.price ?: "0"}", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = TextPrimary) }; Button(onClick = { selectedService?.let { onContinue(it, selectedDate, selectedTime) } }, modifier = Modifier.height(56.dp).width(160.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary)) { Text("Continue", fontWeight = FontWeight.Bold, fontSize = 16.sp) } } } }
    ) { paddingValues -> Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 20.dp).verticalScroll(rememberScrollState())) { Text("Choose service", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 16.dp)); station.services.forEach { service -> SelectableServiceCard(service = service, isSelected = selectedService == service, onClick = { selectedService = service }); Spacer(modifier = Modifier.height(12.dp)) }; Text("Pick a date", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)); Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) { dates.forEach { date -> val (day, num) = date.split(" "); DateItem(day = day, number = num, isSelected = selectedDate == date, onClick = { selectedDate = date }, modifier = Modifier.weight(1f)) } }; Text("Pick a time", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)); Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) { times.forEach { time -> TimeItem(time = time, isSelected = selectedTime == time, onClick = { selectedTime = time }, modifier = Modifier.weight(1f)) } }; Spacer(modifier = Modifier.height(24.dp)) } }
}

@Composable
fun PaymentScreen(station: Station, service: WashService, date: String, time: String, onBack: () -> Unit, onPaymentComplete: () -> Unit) {
    var selectedMethod by remember { mutableStateOf("Visa") }
    var promoCode by remember { mutableStateOf("") }
    val isAndroid = remember { getPlatform().name.contains("Android", ignoreCase = true) }
    Scaffold(
        topBar = { Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }; Text("Payment", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) } },
        bottomBar = { Surface(modifier = Modifier.fillMaxWidth().padding(20.dp), color = Color.Transparent) { PrimaryAuthButton(text = "Pay AED ${service.price}", onClick = onPaymentComplete) } }
    ) { paddingValues -> Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 20.dp).verticalScroll(rememberScrollState())) { Surface(modifier = Modifier.fillMaxWidth(), color = SurfaceColor2, shape = RoundedCornerShape(16.dp)) { Column(modifier = Modifier.padding(20.dp)) { SummaryRow("Service", service.name); SummaryRow("Station", station.name); SummaryRow("Date", "$date · $time"); Spacer(modifier = Modifier.height(16.dp)); Divider(color = Color.Black.copy(alpha = 0.05f)); Spacer(modifier = Modifier.height(16.dp)); Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("Total", fontWeight = FontWeight.Bold, fontSize = 16.sp); Text("AED ${service.price}", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = SuccessGreen) } } }; Text("Payment method", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)); PaymentMethodCard(title = "Visa •• 4521", subtitle = "Default card", icon = Icons.Default.CreditCard, isSelected = selectedMethod == "Visa", onClick = { selectedMethod = "Visa" }); if (!isAndroid) { Spacer(modifier = Modifier.height(12.dp)); PaymentMethodCard(title = "Apple Pay", icon = Icons.Default.Smartphone, isSelected = selectedMethod == "Apple", onClick = { selectedMethod = "Apple" }) }; Spacer(modifier = Modifier.height(12.dp)); PaymentMethodCard(title = "Cash on arrival", icon = Icons.Default.Payments, isSelected = selectedMethod == "Cash", onClick = { selectedMethod = "Cash" }); Text("Promo code", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextSecondary, modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)); Surface(modifier = Modifier.fillMaxWidth().height(56.dp), color = SurfaceColor2, shape = RoundedCornerShape(12.dp)) { Row(modifier = Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) { BasicTextField(value = promoCode, onValueChange = { promoCode = it }, modifier = Modifier.weight(1f), decorationBox = { innerTextField -> if (promoCode.isEmpty()) Text("Enter code", color = TextTertiary, fontSize = 14.sp); innerTextField() }); Text("Apply", color = SuccessGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.clickable { }) } }; Spacer(modifier = Modifier.height(40.dp)) } }
}

@Composable
fun BookingsListScreen(onDetail: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Today (8)", "Tomorrow", "Week")
    
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor)) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Bookings",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Icon(
                Icons.Default.FilterList,
                null,
                modifier = Modifier.size(24.dp),
                tint = TextPrimary
            )
        }

        // Tabs
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                Column(
                    modifier = Modifier.clickable { selectedTab = index },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        title,
                        fontSize = 16.sp,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                        color = if (selectedTab == index) BrandPrimary else TextSecondary
                    )
                    if (selectedTab == index) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .width(80.dp)
                                .height(3.dp)
                                .background(BrandPrimary, RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                        )
                    } else {
                        Spacer(modifier = Modifier.height(11.dp))
                    }
                }
            }
        }
        
        HorizontalDivider(color = Color.Black.copy(alpha = 0.05f))

        // Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            // Section: 11:00 AM
            Text(
                "11:00 AM",
                fontSize = 14.sp,
                color = TextTertiary,
                modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
            )
            OwnerBookingItem(
                name = "Ahmed Khalifa",
                info = "Premium · 75",
                status = "Live",
                statusColor = ErrorRed,
                iconBgColor = Color(0xFF0F6E56),
                onClick = onDetail
            )
            Spacer(modifier = Modifier.height(12.dp))
            OwnerBookingItem(
                name = "Layla Mansour",
                info = "Quick · 35",
                status = "12:00",
                statusColor = WarningAmber,
                iconBgColor = Color(0xFFBA7517),
                onClick = onDetail
            )

            // Section: 2:00 PM
            Text(
                "2:00 PM",
                fontSize = 14.sp,
                color = TextTertiary,
                modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
            )
            OwnerBookingItem(
                name = "Omar Rashid",
                info = "Detailing · 150",
                status = "2:00",
                statusColor = Color(0xFF185FA5),
                iconBgColor = Color(0xFF185FA5),
                onClick = onDetail
            )
            Spacer(modifier = Modifier.height(12.dp))
            OwnerBookingItem(
                name = "Fatima Saif",
                info = "Premium · 75",
                status = "3:30",
                statusColor = Color(0xFFD81B60),
                iconBgColor = Color(0xFFD81B60),
                onClick = onDetail
            )

            // Section: Completed
            Text(
                "Completed",
                fontSize = 14.sp,
                color = TextTertiary,
                modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
            )
            OwnerBookingItem(
                name = "Sara Al Nuaimi",
                info = "Quick · 35",
                status = "Done",
                statusColor = Color(0xFF8E24AA),
                iconBgColor = Color(0xFF8E24AA),
                onClick = onDetail
            )
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun MapContent() {
    Column(modifier = Modifier.fillMaxSize().background(BackgroundColor)) {
        Column(modifier = Modifier.padding(16.dp, 8.dp)) {
            SearchField(value = "", onValueChange = { }, placeholder = "Crystal Clean")
            Row(modifier = Modifier.padding(top = 4.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) { FilterChip(label = "Open now", isSelected = true, onClick = {}, modifier = Modifier) }
        }
        Box(modifier = Modifier.fillMaxWidth().height(170.dp).background(BrandLight)) { Icon(Icons.Default.Map, null, modifier = Modifier.align(Alignment.Center).size(60.dp), tint = BrandPrimary.copy(0.2f)) }
        Column(modifier = Modifier.padding(16.dp)) { Text("3 stations near you", fontSize = 11.sp, color = TextSecondary); Spacer(modifier = Modifier.height(8.dp)); StationCardMini("Aqua Auto Spa", "★ 4.6 · 2.4 km", "45", Color(0xFF185FA5)) }
    }
}

@Composable
fun ProfileContent(onLogout: () -> Unit, onAdmin: () -> Unit) {
    val isOwner = true // This should be determined by user role in a real app
    
    if (isOwner) {
        Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).padding(horizontal = 24.dp).verticalScroll(rememberScrollState())) {
            Text("Settings", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 24.dp))
            
            // Station Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = SurfaceColor,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.05f))
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(60.dp), color = BrandPrimary, shape = RoundedCornerShape(12.dp)) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Store, null, tint = Color.White, modifier = Modifier.size(32.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Crystal Clean Hub", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Verified partner · 2024", fontSize = 13.sp, color = TextSecondary)
                    }
                    Icon(Icons.Default.ChevronRight, null, modifier = Modifier.size(18.dp), tint = TextTertiary)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            Text("BUSINESS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextTertiary, letterSpacing = 1.sp)
            SettingsItem("Station profile", Icons.Default.Info)
            HorizontalDivider(color = Color.Black.copy(alpha = 0.05f))
            SettingsItem("Services & pricing", Icons.Default.GridOn)
            HorizontalDivider(color = Color.Black.copy(alpha = 0.05f))
            SettingsItem("Working hours", Icons.Default.AccessTime)
            HorizontalDivider(color = Color.Black.copy(alpha = 0.05f))
            SettingsItem("Staff & team", Icons.Default.PeopleOutline)
            
            Spacer(modifier = Modifier.height(32.dp))
            Text("ACCOUNT", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextTertiary, letterSpacing = 1.sp)
            SettingsItem("Payouts & bank", Icons.Default.CreditCard)
            HorizontalDivider(color = Color.Black.copy(alpha = 0.05f))
            SettingsItem("Notifications", Icons.Default.NotificationsNone)
            HorizontalDivider(color = Color.Black.copy(alpha = 0.05f))
            SettingsItem("Sign out", Icons.AutoMirrored.Filled.Logout, textColor = ErrorRed, iconColor = ErrorRed, onClick = onLogout)
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    } else {
        Column(modifier = Modifier.fillMaxSize().background(BackgroundColor).padding(20.dp)) {
            Surface(modifier = Modifier.size(100.dp).align(Alignment.CenterHorizontally), color = BrandPrimary, shape = CircleShape) { Box(contentAlignment = Alignment.Center) { Text("AK", color = Color.White, style = MaterialTheme.typography.headlineLarge) } }
            Spacer(modifier = Modifier.height(24.dp)); SettingsItem("Business Profile", Icons.Default.Store); SettingsItem("Services & Pricing", Icons.AutoMirrored.Filled.List); SettingsItem("Payout Methods", Icons.Default.Payments); SettingsItem("Help & Support", Icons.AutoMirrored.Filled.Help); Spacer(modifier = Modifier.weight(1f)); Button(onClick = onLogout, modifier = Modifier.fillMaxWidth().height(50.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEE2E2), contentColor = ErrorRed), shape = RoundedCornerShape(12.dp)) { Text("Logout") }
        }
    }
}

@Composable
fun AdminPanelScreen(onBack: () -> Unit) { /* existing admin implementation */ }

@Composable
fun BookingDetailScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).verticalScroll(rememberScrollState())) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }
            Text("Booking #2847", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }

        // Status Card
        Surface(
            modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
            color = BrandLight,
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("IN PROGRESS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = BrandPrimary)
                Text("Started 11:32", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.padding(vertical = 4.dp))
                Text("8 min remaining", fontSize = 14.sp, color = BrandPrimary)
            }
        }

        // Customer Section
        Text("Customer", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 12.dp), fontSize = 16.sp)
        Surface(
            modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
            color = SurfaceColor,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.05f))
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Surface(modifier = Modifier.size(48.dp), color = BrandLight, shape = CircleShape) {
                    Box(contentAlignment = Alignment.Center) { Text("AK", fontWeight = FontWeight.Bold, color = BrandPrimary) }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Ahmed Khalifa", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, null, modifier = Modifier.size(14.dp), tint = AccentOrange)
                        Text(" 4.9 · 12 bookings", fontSize = 13.sp, color = TextSecondary)
                    }
                }
                IconButton(onClick = {}) { Icon(Icons.Default.Phone, null, tint = BrandPrimary, modifier = Modifier.size(20.dp)) }
            }
        }

        // Vehicle & Service Section
        Text("Vehicle & service", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 12.dp), fontSize = 16.sp)
        Surface(
            modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
            color = SurfaceColor,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.05f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                SummaryRow("Vehicle", "Toyota Camry · White")
                SummaryRow("Plate", "AUH 12345")
                SummaryRow("Service", "Premium Wash")
                SummaryRow("Amount", "AED 75", valueColor = SuccessGreen, isBold = true)
            }
        }

        // Action Buttons
        Row(
            modifier = Modifier.padding(24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, BrandPrimary)
            ) {
                Text("Pause", color = BrandPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Button(
                onClick = {},
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary)
            ) {
                Text("Complete", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun AnalyticsScreen() {
    Column(modifier = Modifier.fillMaxSize().background(BackgroundColor).padding(16.dp)) {
        Text("Revenue Analytics", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Surface(modifier = Modifier.padding(top = 16.dp).fillMaxWidth().height(150.dp), color = BrandPrimary, shape = RoundedCornerShape(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) { Text("This Month", color = Color.White.copy(0.7f), fontSize = 11.sp); Text("AED 24,850", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold); Spacer(modifier = Modifier.height(10.dp)); Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(8.dp)) { (1..7).forEach { Box(modifier = Modifier.weight(1f).height((30..80).random().dp).background(Color.White.copy(0.4f), RoundedCornerShape(4.dp))) } } }
        }
    }
}

@Composable
fun ReviewsScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).verticalScroll(rememberScrollState())) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }
            Text("Reviews", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }

        // Summary Card
        Surface(
            modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
            color = BrandLight.copy(alpha = 0.5f),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("4.8", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = BrandPrimary, fontFamily = FontFamily.Serif)
                    Row { repeat(5) { Icon(Icons.Default.Star, null, modifier = Modifier.size(14.dp), tint = AccentOrange) } }
                    Text("320 reviews", fontSize = 12.sp, color = TextSecondary, modifier = Modifier.padding(top = 4.dp))
                }
                Spacer(modifier = Modifier.width(24.dp))
                Column(modifier = Modifier.weight(1f)) {
                    RatingProgressRow(5, 0.78f, "78%")
                    RatingProgressRow(4, 0.15f, "15%")
                    RatingProgressRow(3, 0.05f, "5%")
                    RatingProgressRow(2, 0.02f, "2%")
                }
            }
        }

        // Reviews List
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ReviewCard(
                initials = "AK",
                name = "Ahmed K.",
                rating = 5,
                time = "2d ago",
                text = "Spotless finish, friendly staff. Will return."
            )
            ReviewCard(
                initials = "FS",
                name = "Fatima S.",
                rating = 4,
                time = "4d ago",
                text = "Great service, slight delay but worth it."
            )
        }
    }
}

@Composable
fun RatingProgressRow(stars: Int, progress: Float, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 2.dp)) {
        Text(stars.toString(), fontSize = 12.sp, color = TextPrimary, modifier = Modifier.width(12.dp))
        Spacer(modifier = Modifier.width(8.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.weight(1f).height(6.dp).clip(RoundedCornerShape(3.dp)),
            color = BrandPrimary,
            trackColor = Color.Black.copy(alpha = 0.05f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(label, fontSize = 12.sp, color = TextSecondary, modifier = Modifier.width(30.dp), textAlign = TextAlign.End)
    }
}

@Composable
fun ReviewCard(initials: String, name: String, rating: Int, time: String, text: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = SurfaceColor,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.05f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(modifier = Modifier.size(36.dp), color = BrandLight, shape = CircleShape) {
                    Box(contentAlignment = Alignment.Center) { Text(initials, fontWeight = FontWeight.Bold, color = BrandPrimary, fontSize = 12.sp) }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(5) { i ->
                            Icon(
                                Icons.Default.Star,
                                null,
                                modifier = Modifier.size(10.dp),
                                tint = if (i < rating) AccentOrange else Color.Black.copy(alpha = 0.1f)
                            )
                        }
                        Text(" · $time", fontSize = 11.sp, color = TextSecondary)
                    }
                }
            }
            Text(text, fontSize = 14.sp, color = TextSecondary, modifier = Modifier.padding(top = 12.dp))
        }
    }
}

// --- OWNER FLOW ---

@Composable
fun PartnerLoginScreen(onSignIn: () -> Unit, onRegister: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).padding(24.dp).verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(40.dp)); Surface(modifier = Modifier.fillMaxWidth().height(160.dp), color = BrandLight, shape = RoundedCornerShape(20.dp)) { Box(contentAlignment = Alignment.Center) { Icon(imageVector = Icons.Default.Store, contentDescription = null, modifier = Modifier.size(64.dp), tint = BrandPrimary) } }; Spacer(modifier = Modifier.height(32.dp)); Text(text = "SparkleWash Partners", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif), color = TextPrimary, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center); Text(text = "Grow your car wash business", fontSize = 16.sp, color = TextSecondary, modifier = Modifier.fillMaxWidth().padding(top = 8.dp), textAlign = TextAlign.Center); Spacer(modifier = Modifier.height(40.dp)); AuthTextField(value = email, onValueChange = { email = it }, label = "Business email", placeholder = "owner@yourstation.com"); Spacer(modifier = Modifier.height(16.dp)); AuthTextField(value = password, onValueChange = { password = it }, label = "Password", placeholder = "••••••••", isPasswordField = true); Spacer(modifier = Modifier.height(32.dp)); PrimaryAuthButton(text = "Sign in", onClick = onSignIn); Spacer(modifier = Modifier.height(12.dp)); OutlinedButton(onClick = onRegister, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(16.dp), border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.1f))) { Text("Register your station", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp) }
    }
}

@Composable
fun StationRegisterStep1Screen(onBack: () -> Unit, onContinue: () -> Unit) {
    var bizName by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var license by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).padding(24.dp).verticalScroll(rememberScrollState())) {
        Row(verticalAlignment = Alignment.CenterVertically) { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }; Text("Register station", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) }; Spacer(modifier = Modifier.height(16.dp)); LinearProgressIndicator(progress = { 0.33f }, modifier = Modifier.fillMaxWidth().height(4.dp).background(SurfaceColor2, RoundedCornerShape(2.dp)), color = BrandPrimary); Text("Step 1 of 3 — About your business", fontSize = 14.sp, color = TextSecondary, modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)); AuthTextField(value = bizName, onValueChange = { bizName = it }, label = "Business name", placeholder = "Crystal Clean Hub LLC"); Spacer(modifier = Modifier.height(16.dp)); AuthTextField(value = ownerName, onValueChange = { ownerName = it }, label = "Owner name", placeholder = "Mohammed Al Mansouri"); Spacer(modifier = Modifier.height(16.dp)); AuthTextField(value = license, onValueChange = { license = it }, label = "Trade license", placeholder = "CN-1234567"); Spacer(modifier = Modifier.height(16.dp)); AuthTextField(value = phone, onValueChange = { phone = it }, label = "Contact phone", placeholder = "+971 50 987 6543"); Spacer(modifier = Modifier.height(16.dp)); AuthTextField(value = email, onValueChange = { email = it }, label = "Email", placeholder = "crystal@clean.ae"); Spacer(modifier = Modifier.height(40.dp)); PrimaryAuthButton(text = "Continue", onClick = onContinue)
    }
}

@Composable
fun StationRegisterStep2Screen(onBack: () -> Unit, onContinue: () -> Unit) {
    val servicesList = listOf("Exterior", "Interior", "Detailing", "Polish", "Wax")
    var selectedServices by remember { mutableStateOf(setOf("Exterior", "Interior", "Detailing")) }
    var openTime by remember { mutableStateOf("8:00 AM") }
    var closeTime by remember { mutableStateOf("10:00 PM") }
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).padding(24.dp).verticalScroll(rememberScrollState())) {
        Row(verticalAlignment = Alignment.CenterVertically) { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }; Text("Register station", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) }; Spacer(modifier = Modifier.height(16.dp)); LinearProgressIndicator(progress = { 0.66f }, modifier = Modifier.fillMaxWidth().height(4.dp).background(SurfaceColor2, RoundedCornerShape(2.dp)), color = BrandPrimary); Text("Step 2 of 3 — Services & pricing", fontSize = 14.sp, color = TextSecondary, modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)); Text("Services offered", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary); Spacer(modifier = Modifier.height(12.dp)); FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) { servicesList.forEach { service -> FilterChip(label = service, isSelected = selectedServices.contains(service), onClick = { selectedServices = if (selectedServices.contains(service)) selectedServices - service else selectedServices + service }, modifier = Modifier.padding(bottom = 8.dp)) } }; Spacer(modifier = Modifier.height(24.dp)); Text("Pricing", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary); Spacer(modifier = Modifier.height(12.dp)); PricingInputRow("Quick Exterior", "35"); PricingInputRow("Premium Wash", "75"); PricingInputRow("Detailing Plus", "150"); Spacer(modifier = Modifier.height(24.dp)); Text("Working hours", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary); Spacer(modifier = Modifier.height(12.dp)); Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) { Box(modifier = Modifier.weight(1f)) { AuthTextField(value = openTime, onValueChange = { openTime = it }, label = "", placeholder = "8:00 AM") }; Box(modifier = Modifier.weight(1f)) { AuthTextField(value = closeTime, onValueChange = { closeTime = it }, label = "", placeholder = "10:00 PM") } }; Spacer(modifier = Modifier.height(40.dp)); PrimaryAuthButton(text = "Continue", onClick = onContinue)
    }
}

@Composable
fun StationRegisterStep3Screen(onBack: () -> Unit, onComplete: () -> Unit) {
    var location by remember { mutableStateOf("Al Reem Island, Abu Dhabi") }
    Column(modifier = Modifier.fillMaxSize().background(SurfaceColor).padding(24.dp).verticalScroll(rememberScrollState())) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }
            Text("Register station", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = { 1.0f },
            modifier = Modifier.fillMaxWidth().height(4.dp).background(SurfaceColor2, RoundedCornerShape(2.dp)),
            color = BrandPrimary
        )
        Text("Step 3 of 3 — Location & Photos", fontSize = 14.sp, color = TextSecondary, modifier = Modifier.padding(top = 12.dp, bottom = 24.dp))
        
        Text("Station Location", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Spacer(modifier = Modifier.height(12.dp))
        Surface(modifier = Modifier.fillMaxWidth().height(180.dp), color = BrandLight, shape = RoundedCornerShape(16.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Map, null, modifier = Modifier.size(48.dp), tint = BrandPrimary.copy(alpha = 0.4f))
                Text("Map Picker Placeholder", modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp), fontSize = 12.sp, color = BrandPrimary)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        AuthTextField(value = location, onValueChange = { location = it }, label = "Street Address", placeholder = "Sector 4, Building 12")
        
        Spacer(modifier = Modifier.height(24.dp))
        Text("Station Photos", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(3) {
                Surface(modifier = Modifier.size(80.dp), color = SurfaceColor2, shape = RoundedCornerShape(12.dp), border = BorderStroke(1.dp, Color.Black.copy(0.05f))) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.AddAPhoto, null, tint = TextTertiary, modifier = Modifier.size(24.dp))
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(40.dp))
        PrimaryAuthButton(text = "Complete Registration", onClick = onComplete)
    }
}

@Composable
fun OwnerDashboardScreen(onLogout: () -> Unit, onBookingDetail: () -> Unit, onSeeReviews: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = SurfaceColor, tonalElevation = 0.dp) {
                listOf(
                    "Home" to Icons.Default.GridView,
                    "Books" to Icons.Default.CalendarToday,
                    "Stats" to Icons.Default.BarChart,
                    "More" to Icons.Default.Settings
                ).forEachIndexed { index, (label, icon) ->
                    NavigationBarItem(
                        icon = { Icon(icon, null) },
                        label = { Text(label, fontSize = 10.sp) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = BrandPrimary,
                            selectedTextColor = BrandPrimary,
                            unselectedIconColor = TextTertiary,
                            unselectedTextColor = TextTertiary,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                0 -> OwnerHomeContent(onBookingDetail, onSeeReviews)
                1 -> BookingsListScreen(onBookingDetail)
                2 -> AnalyticsScreen()
                3 -> ProfileContent(onLogout = onLogout, onAdmin = {})
            }
        }
    }
}

@Composable
fun OwnerHomeContent(onBookingDetail: () -> Unit, onSeeReviews: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceColor)
            .verticalScroll(rememberScrollState())
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(44.dp), color = BrandLight, shape = RoundedCornerShape(10.dp)) {
                Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Store, null, tint = BrandPrimary) }
            }
            Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                Text("Crystal Clean", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(6.dp).background(SuccessGreen, CircleShape))
                    Text(" Open", fontSize = 12.sp, color = SuccessGreen, modifier = Modifier.padding(start = 4.dp))
                }
            }
            IconButton(onClick = {}) { Icon(Icons.Default.Notifications, null, tint = TextPrimary) }
        }
        Surface(
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            color = BrandPrimary,
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Today's earnings", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                Text("AED 1,240", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 4.dp))
                Text("+ 12% vs yesterday", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OwnerStatBox("18", "Bookings", Modifier.weight(1f))
            OwnerStatBox("4.8", "Rating", Modifier.weight(1f), onClick = onSeeReviews)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OwnerStatBox("92%", "Completion", Modifier.weight(1f))
            OwnerStatBox("26m", "Avg time", Modifier.weight(1f))
        }
        Text(
            "Up next",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp, top = 24.dp, bottom = 16.dp)
        )
        Column(modifier = Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OwnerBookingItem("Ahmed K.", "11:30 · Premium", "Soon", SuccessGreen, BrandPrimary, onClick = onBookingDetail)
            OwnerBookingItem("Layla M.", "12:00 · Quick", "Wait", WarningAmber, AccentOrange, onClick = onBookingDetail)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
