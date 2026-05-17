package org.example.project

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.ui.*

@Composable
@Preview
fun App() {
    WashProTheme {
        var hasSeenOnboarding by remember { mutableStateOf(false) }
        var currentScreen by remember { 
            mutableStateOf<Screen>(if (hasSeenOnboarding) Screen.Login else Screen.Splash) 
        }

        when (currentScreen) {
            is Screen.Splash -> {
                SplashScreen(onTimeout = {
                    currentScreen = if (hasSeenOnboarding) Screen.Login else Screen.Onboarding
                })
            }
            is Screen.Onboarding -> {
                OnboardingScreen(onFinish = {
                    hasSeenOnboarding = true
                    currentScreen = Screen.Login
                })
            }
            is Screen.Login -> {
                LoginScreen(onLogin = {
                    currentScreen = Screen.CarType
                })
            }
            is Screen.CarType -> {
                CarTypeScreen(
                    onContinue = { currentScreen = Screen.Subscription },
                    onBack = { currentScreen = Screen.Login }
                )
            }
            is Screen.Subscription -> {
                SubscriptionScreen(
                    onSubscribe = { currentScreen = Screen.Payment },
                    onBack = { currentScreen = Screen.CarType }
                )
            }
            is Screen.Payment -> {
                PaymentScreen(
                    onPay = { currentScreen = Screen.Success },
                    onBack = { currentScreen = Screen.Subscription }
                )
            }
            is Screen.Success -> {
                SuccessScreen(
                    onDashboard = { currentScreen = Screen.Dashboard },
                    onSchedule = { currentScreen = Screen.Dashboard } // Will go to dashboard, we can set tab later if needed
                )
            }
            is Screen.Dashboard -> {
                DashboardScreen(onLogout = {
                    currentScreen = Screen.Login
                })
            }
            // These are now handled inside DashboardScreen as tabs
            is Screen.Schedule -> {
                currentScreen = Screen.Dashboard
            }
            is Screen.Manage -> {
                currentScreen = Screen.Dashboard
            }
        }
    }
}
