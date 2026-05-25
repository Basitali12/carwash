package org.example.project.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.ui.*

@Composable
fun AccountTypeCard(
    title: String,
    description: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) SuccessGreen.copy(alpha = 0.05f) else SurfaceColor,
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) SuccessGreen else Color.Black.copy(alpha = 0.05f)
        )
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                color = if (isSelected) SuccessGreen else SurfaceColor2,
                shape = RoundedCornerShape(12.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        icon,
                        null,
                        modifier = Modifier.size(24.dp),
                        tint = if (isSelected) Color.White else TextSecondary
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
                Text(description, fontSize = 13.sp, color = TextSecondary)
            }
            if (isSelected) {
                Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen)
            }
        }
    }
}

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(56.dp),
        color = SurfaceColor2,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, null, tint = TextTertiary, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) Text(placeholder, color = TextTertiary, fontSize = 15.sp)
                    innerTextField()
                }
            )
        }
    }
}

@Composable
fun ServiceItem(
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(60.dp),
            color = SurfaceColor2,
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, null, modifier = Modifier.size(24.dp), tint = BrandPrimary)
            }
        }
        Text(
            label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = TextSecondary,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun StationRow(
    station: Station,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = SurfaceColor,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.05f))
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(BrandLight, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Image, null, tint = BrandPrimary.copy(alpha = 0.3f))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(station.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("${station.location} · ${station.distance}", fontSize = 13.sp, color = TextSecondary)
                Row(modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, null, modifier = Modifier.size(14.dp), tint = AccentOrange)
                    Text(
                        station.rating,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
            Icon(Icons.Default.ChevronRight, null, tint = TextTertiary)
        }
    }
}

@Composable
fun FilterChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(40.dp).clickable(onClick = onClick),
        color = if (isSelected) BrandPrimary else SurfaceColor,
        shape = RoundedCornerShape(20.dp),
        border = if (isSelected) null else BorderStroke(1.dp, Color.Black.copy(alpha = 0.1f))
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                label,
                color = if (isSelected) Color.White else TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun MapMarker(
    modifier: Modifier = Modifier,
    color: Color = BrandPrimary,
    isLarge: Boolean = false
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier.size(if (isLarge) 40.dp else 24.dp),
            color = color.copy(alpha = 0.2f),
            shape = CircleShape
        ) {}
        Surface(
            modifier = Modifier.size(if (isLarge) 16.dp else 10.dp),
            color = color,
            shape = CircleShape,
            border = BorderStroke(2.dp, Color.White)
        ) {}
    }
}

@Composable
fun ExploreStationCard(
    station: Station,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = SurfaceColor,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) BrandPrimary else Color.Black.copy(alpha = 0.05f)
        )
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(BrandLight, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Store, null, tint = BrandPrimary.copy(alpha = 0.4f))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(station.name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    if (station.status == VerificationStatus.VERIFIED) {
                        Icon(
                            Icons.Default.Verified,
                            null,
                            modifier = Modifier.size(14.dp).padding(start = 4.dp),
                            tint = Color(0xFF1D9E75)
                        )
                    }
                }
                Text("${station.location} · ${station.distance}", fontSize = 12.sp, color = TextSecondary)
                Row(modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, null, modifier = Modifier.size(12.dp), tint = AccentOrange)
                    Text(station.rating, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("AED 35+", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SuccessGreen)
                }
            }
        }
    }
}

@Composable
fun DetailBadge(icon: ImageVector, text: String) {
    Surface(
        color = SurfaceColor2,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, modifier = Modifier.size(14.dp), tint = TextSecondary)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
        }
    }
}

@Composable
fun StatusBadge(text: String) {
    Surface(
        color = SuccessGreen.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            color = SuccessGreen,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun ServiceDetailCard(service: WashService) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        color = SurfaceColor,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.05f))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(service.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(service.description, fontSize = 13.sp, color = TextSecondary)
            }
            Text("AED ${service.price}", fontWeight = FontWeight.Bold, color = BrandPrimary)
        }
    }
}

@Composable
fun SelectableServiceCard(service: WashService, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        color = if (isSelected) BrandPrimary.copy(alpha = 0.05f) else SurfaceColor,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) BrandPrimary else Color.Black.copy(alpha = 0.05f)
        )
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(service.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(service.description, fontSize = 13.sp, color = TextSecondary)
            }
            Text("AED ${service.price}", fontWeight = FontWeight.Bold, color = if (isSelected) BrandPrimary else TextPrimary)
        }
    }
}

@Composable
fun DateItem(day: String, number: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        color = if (isSelected) BrandPrimary else SurfaceColor2,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(day, fontSize = 10.sp, color = if (isSelected) Color.White.copy(0.7f) else TextSecondary)
            Text(number, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if (isSelected) Color.White else TextPrimary)
        }
    }
}

@Composable
fun TimeItem(time: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        color = if (isSelected) BrandPrimary else SurfaceColor2,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 12.dp)) {
            Text(time, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = if (isSelected) Color.White else TextPrimary)
        }
    }
}

@Composable
fun SummaryRow(
    label: String,
    value: String,
    valueColor: Color = TextPrimary,
    isBold: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = TextSecondary, fontSize = 14.sp)
        Text(
            value,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Medium,
            fontSize = 14.sp,
            color = valueColor
        )
    }
}

@Composable
fun PaymentMethodCard(title: String, subtitle: String? = null, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        color = if (isSelected) BrandPrimary.copy(alpha = 0.05f) else SurfaceColor,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(if (isSelected) 2.dp else 1.dp, if (isSelected) BrandPrimary else Color.Black.copy(alpha = 0.05f))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = if (isSelected) BrandPrimary else TextSecondary)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                if (subtitle != null) Text(subtitle, fontSize = 12.sp, color = TextTertiary)
            }
            RadioButton(selected = isSelected, onClick = onClick, colors = RadioButtonDefaults.colors(selectedColor = BrandPrimary))
        }
    }
}

@Composable
fun TabItem(label: String, isSelected: Boolean) {
    Column(modifier = Modifier.padding(end = 20.dp)) {
        Text(label, color = if (isSelected) BrandPrimary else TextSecondary, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal, fontSize = 14.sp)
        if (isSelected) Box(modifier = Modifier.padding(top = 4.dp).size(20.dp, 2.dp).background(BrandPrimary, CircleShape))
    }
}

@Composable
fun StationCardMini(name: String, info: String, price: String, iconColor: Color) {
    Surface(modifier = Modifier.fillMaxWidth(), color = SurfaceColor, shape = RoundedCornerShape(12.dp), border = BorderStroke(1.dp, Color.Black.copy(0.05f))) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(40.dp).background(iconColor.copy(0.1f), CircleShape), contentAlignment = Alignment.Center) { Icon(Icons.Default.LocalDrink, null, tint = iconColor, modifier = Modifier.size(20.dp)) }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) { Text(name, fontWeight = FontWeight.Bold, fontSize = 14.sp); Text(info, fontSize = 11.sp, color = TextSecondary) }
            Text("AED $price", fontWeight = FontWeight.Bold, color = BrandPrimary, fontSize = 14.sp)
        }
    }
}

@Composable
fun SettingsItem(
    label: String,
    icon: ImageVector,
    textColor: Color = TextPrimary,
    iconColor: Color = TextSecondary,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, modifier = Modifier.size(22.dp), tint = iconColor)
        Text(
            label,
            modifier = Modifier.padding(start = 16.dp).weight(1f),
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = textColor
        )
        if (textColor != ErrorRed) {
            Icon(Icons.Default.ChevronRight, null, modifier = Modifier.size(18.dp), tint = TextTertiary)
        }
    }
}

@Composable
fun TimelineItemDesign(title: String, time: String, isDone: Boolean, isLive: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(modifier = Modifier.size(20.dp), color = if (isDone) BrandPrimary else if (isLive) SuccessGreen else Color.Transparent, border = if (isDone || isLive) null else BorderStroke(2.dp, TextTertiary), shape = CircleShape) {
                if (isDone) Icon(Icons.Default.Check, null, modifier = Modifier.padding(4.dp), tint = Color.White)
            }
            Box(modifier = Modifier.width(2.dp).height(30.dp).background(if (isDone) BrandPrimary else TextTertiary.copy(0.3f)))
        }
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(title, fontWeight = if (isLive) FontWeight.Bold else FontWeight.Normal, color = if (isLive) SuccessGreen else TextPrimary)
            Text(time, fontSize = 11.sp, color = TextSecondary)
        }
    }
}

@Composable
fun ReviewItemDesign() {
    Column(modifier = Modifier.padding(bottom = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(32.dp), color = SurfaceColor2, shape = CircleShape) { Box(contentAlignment = Alignment.Center) { Text("JD", fontSize = 12.sp) } }
            Spacer(modifier = Modifier.width(10.dp))
            Column { Text("John Doe", fontWeight = FontWeight.Bold, fontSize = 13.sp); Text("2 days ago", fontSize = 10.sp, color = TextTertiary) }
            Spacer(modifier = Modifier.weight(1f))
            Text("★★★★★", color = AccentOrange, fontSize = 12.sp)
        }
        Text("Amazing service! My car looks brand new. Highly recommended.", fontSize = 13.sp, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun PricingInputRow(label: String, defaultValue: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, modifier = Modifier.weight(1f), fontSize = 14.sp)
        Surface(modifier = Modifier.width(100.dp).height(44.dp), color = SurfaceColor2, shape = RoundedCornerShape(10.dp)) {
            Row(modifier = Modifier.padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("AED", fontSize = 12.sp, color = TextTertiary)
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(value = defaultValue, onValueChange = {}, modifier = Modifier.weight(1f), textStyle = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold))
            }
        }
    }
}

@Composable
fun OwnerStatBox(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        color = SurfaceColor,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.05f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text(label, fontSize = 12.sp, color = TextSecondary)
        }
    }
}

@Composable
fun OwnerBookingItem(
    name: String,
    info: String,
    status: String,
    statusColor: Color,
    iconBgColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxWidth().clickable(onClick = onClick),
        color = SurfaceColor,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.05f))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                color = iconBgColor.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.DirectionsCar,
                        null,
                        modifier = Modifier.size(20.dp),
                        tint = iconBgColor
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
                Text(info, fontSize = 13.sp, color = TextSecondary)
            }
            Surface(
                color = statusColor.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = status,
                    color = statusColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}
