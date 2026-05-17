package org.example.project.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color

data class UserProfile(
    val name: String,
    val email: String,
    val phone: String,
    val avatar: String
)

val MockUser = UserProfile(
    name = "Ahmed Al Rashid",
    email = "ahmed.rashid@example.com",
    phone = "+971 50 123 4567",
    avatar = "AA"
)

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
    WashBooking("17", "MAY", "Full Exterior Wash", "10:00 AM", "Home Parking", "Scheduled", Color(0xFF15803D)),
    WashBooking("22", "MAY", "Interior + Exterior", "02:00 PM", "Office Parking", "Confirmed", Color(0xFF15803D)),
    WashBooking("28", "MAY", "Express Wash", "09:00 AM", "Home Parking", "Pending", Color(0xFFB45309))
)

val MockQuickActions = listOf(
    QuickActionItem("Schedule Wash", Icons.Default.CalendarMonth, Color(0xFFDBEAFE), Color(0xFF2563EB)),
    QuickActionItem("My Cars", Icons.Default.DirectionsCar, Color(0xFFDCFCE7), Color(0xFF16A34A)),
    QuickActionItem("Payments", Icons.Default.CreditCard, Color(0xFFFEF3C7), Color(0xFFB45309)),
    QuickActionItem("Subscription", Icons.Default.Settings, Color(0xFFF3E8FF), Color(0xFF7C3AED))
)
