package org.example.project

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.ui.*

@Composable
@Preview
fun App() {
    WashProTheme {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
        var userPhone by remember { mutableStateOf("") }
        var userRole by remember { mutableStateOf(UserRole.CUSTOMER) }

        when (currentScreen) {
            is Screen.Splash -> {
                SplashScreen(onTimeout = { currentScreen = Screen.Onboarding })
            }
            is Screen.Onboarding -> {
                OnboardingScreen(onFinish = { currentScreen = Screen.PhoneEntry })
            }
            is Screen.PhoneEntry -> {
                PhoneEntryScreen(
                    onSendOtp = { 
                        userPhone = it
                        currentScreen = Screen.OtpVerify 
                    },
                    onSignUp = { currentScreen = Screen.SignUp },
                    onBack = { currentScreen = Screen.Onboarding }
                )
            }
            is Screen.SignUp -> {
                SignUpScreen(
                    onSignUpComplete = {
                        userPhone = it
                        currentScreen = Screen.OtpVerify
                    },
                    onSignIn = { currentScreen = Screen.PhoneEntry },
                    onBack = { currentScreen = Screen.PhoneEntry }
                )
            }
            is Screen.OtpVerify -> {
                OtpVerifyScreen(
                    phone = userPhone,
                    onVerify = { 
                        CurrentUser = CurrentUser.copy(phone = userPhone, isVerified = true)
                        currentScreen = Screen.RoleSelection 
                    },
                    onBack = { currentScreen = Screen.PhoneEntry }
                )
            }
            is Screen.RoleSelection -> {
                RoleSelectionScreen(
                    onRoleSelected = { role ->
                        userRole = role
                        CurrentUser = CurrentUser.copy(role = role)
                        if (role == UserRole.STATION_OWNER) {
                            currentScreen = Screen.PartnerLogin
                        } else {
                            currentScreen = Screen.Dashboard
                        }
                    },
                    onBack = { currentScreen = Screen.OtpVerify }
                )
            }
            is Screen.PartnerLogin -> {
                PartnerLoginScreen(
                    onSignIn = { currentScreen = Screen.OwnerDashboard },
                    onRegister = { currentScreen = Screen.StationRegisterStep1 }
                )
            }
            is Screen.StationRegisterStep1 -> {
                StationRegisterStep1Screen(
                    onBack = { currentScreen = Screen.PartnerLogin },
                    onContinue = { currentScreen = Screen.StationRegisterStep2 }
                )
            }
            is Screen.StationRegisterStep2 -> {
                StationRegisterStep2Screen(
                    onBack = { currentScreen = Screen.StationRegisterStep1 },
                    onContinue = { currentScreen = Screen.StationRegisterStep3 }
                )
            }
            is Screen.StationRegisterStep3 -> {
                StationRegisterStep3Screen(
                    onBack = { currentScreen = Screen.StationRegisterStep2 },
                    onComplete = { currentScreen = Screen.OwnerDashboard }
                )
            }
            is Screen.OwnerDashboard -> {
                OwnerDashboardScreen(
                    onLogout = { currentScreen = Screen.PartnerLogin },
                    onBookingDetail = { currentScreen = Screen.BookingDetail },
                    onSeeReviews = { currentScreen = Screen.Reviews }
                )
            }
            is Screen.Dashboard -> {
                DashboardScreen(
                    onLogout = { currentScreen = Screen.PhoneEntry },
                    onAdmin = { currentScreen = Screen.AdminPanel },
                    onStationDetail = { station ->
                        currentScreen = Screen.StationDetail(station)
                    },
                    onSeeAll = { currentScreen = Screen.ExploreStations }
                )
            }
            is Screen.ExploreStations -> {
                ExploreStationsScreen(
                    onBack = { currentScreen = Screen.Dashboard },
                    onDetail = { station ->
                        currentScreen = Screen.StationDetail(station)
                    }
                )
            }
            is Screen.StationDetail -> {
                val station = (currentScreen as Screen.StationDetail).station
                StationDetailScreen(
                    station = station,
                    onBack = { currentScreen = Screen.Dashboard },
                    onBook = { currentScreen = Screen.Booking(station) }
                )
            }
            is Screen.Booking -> {
                val station = (currentScreen as Screen.Booking).station
                BookingScreen(
                    station = station,
                    onBack = { currentScreen = Screen.StationDetail(station) },
                    onContinue = { s, d, t ->
                        currentScreen = Screen.Payment(station, s, d, t)
                    }
                )
            }
            is Screen.Payment -> {
                val p = currentScreen as Screen.Payment
                PaymentScreen(
                    station = p.station,
                    service = p.service,
                    date = p.date,
                    time = p.time,
                    onBack = { currentScreen = Screen.Booking(p.station) },
                    onPaymentComplete = { currentScreen = Screen.Dashboard }
                )
            }
            is Screen.AdminPanel -> {
                AdminPanelScreen(onBack = { currentScreen = Screen.Dashboard })
            }
            is Screen.BookingDetail -> {
                BookingDetailScreen(onBack = { currentScreen = Screen.Dashboard })
            }
            is Screen.Analytics -> {
                AnalyticsScreen()
            }
            is Screen.Reviews -> {
                ReviewsScreen(onBack = { currentScreen = Screen.OwnerDashboard })
            }
        }
    }
}
