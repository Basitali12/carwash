package org.example.project.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// --- MODELS ---
data class OnboardingPage(val title: String, val description: String, val icon: ImageVector)
data class CarOption(val label: String, val icon: String)
data class PlanOption(val name: String, val price: String, val features: List<String>, val isPopular: Boolean = false, val isPremium: Boolean = false)
data class QuickActionItem(val label: String, val icon: ImageVector, val bgColor: Color, val iconColor: Color)
data class ManageOption(val title: String, val description: String, val icon: ImageVector, val bgColor: Color, val iconColor: Color)

// --- ROLES ---
enum class UserRole { CUSTOMER, STATION_OWNER, ADMIN }
enum class VerificationStatus { PENDING, VERIFIED, REJECTED }

// --- USER ---
data class UserProfile(
    val name: String,
    val phone: String,
    val email: String,
    val isVerified: Boolean = false,
    val role: UserRole = UserRole.CUSTOMER,
    val avatar: String = "AA"
)

var CurrentUser = UserProfile(
    name = "Ahmed Al Rashid",
    phone = "+971 50 123 4567",
    email = "ahmed@example.com",
    isVerified = false
)

data class WashService(
    val name: String,
    val description: String,
    val price: String
)

// --- STATION ---
data class Station(
    val id: String,
    val name: String,
    val location: String,
    val distance: String,
    val status: VerificationStatus = VerificationStatus.PENDING,
    val rating: String = "4.8",
    val imageUrl: String = "",
    val services: List<WashService> = emptyList()
)

val MockStations = mutableListOf(
    Station(
        id = "1",
        name = "Crystal Clean Hub",
        location = "Al Reem Island",
        distance = "1.2 km",
        status = VerificationStatus.VERIFIED,
        rating = "4.8",
        services = listOf(
            WashService("Quick Exterior", "Foam · rinse · dry", "35"),
            WashService("Premium Wash", "Exterior + interior", "75")
        )
    ),
    Station(
        id = "2",
        name = "Aqua Auto Spa",
        location = "Downtown",
        distance = "2.4 km",
        status = VerificationStatus.VERIFIED,
        rating = "4.6",
        services = listOf(
            WashService("Quick Exterior", "Foam · rinse · dry", "45"),
            WashService("Premium Wash", "Exterior + interior", "85")
        )
    ),
    Station("3", "Desert Cleaners", "Jumeirah", "0.8 km", VerificationStatus.PENDING),
    Station("4", "Luxury Auto Care", "Marina", "5.0 km", VerificationStatus.REJECTED)
)

// --- DASHBOARD UI HELPERS ---
data class WashBooking(
    val day: String,
    val month: String,
    val type: String,
    val time: String,
    val location: String,
    val status: String,
    val statusColor: Color
)

val MockUpcomingWashes = listOf(
    WashBooking("17", "MAY", "Full Exterior Wash", "10:00 AM", "Elite Car Spa", "Scheduled", Color(0xFF15803D)),
    WashBooking("22", "MAY", "Interior + Exterior", "02:00 PM", "Quick Wash Pro", "Confirmed", Color(0xFF15803D))
)

val MockQuickActions = listOf(
    QuickActionItem("Schedule Wash", Icons.Default.CalendarMonth, Color(0xFFDBEAFE), Color(0xFF2563EB)),
    QuickActionItem("My Cars", Icons.Default.DirectionsCar, Color(0xFFDCFCE7), Color(0xFF16A34A)),
    QuickActionItem("Payments", Icons.Default.CreditCard, Color(0xFFFEF3C7), Color(0xFFB45309)),
    QuickActionItem("Subscription", Icons.Default.Settings, Color(0xFFF3E8FF), Color(0xFF7C3AED))
)
