#
# Copyright (C) 2021-2025 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit_only.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit from waffle device
$(call inherit-product, device/oneplus/avalon/device.mk)
TARGET_BOOT_ANIMATION_RES := 1080
DERPFEST_BUILD_TYPE := Official
TARGET_USES_BLUR := true

# Inherit some common Lineage stuff.
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

PRODUCT_NAME := lineage_avalon
PRODUCT_DEVICE := avalon
PRODUCT_MANUFACTURER := OnePlus
PRODUCT_BRAND := OnePlus
PRODUCT_MODEL := CPH2661

PRODUCT_GMS_CLIENTID_BASE := android-oneplus

PRODUCT_BUILD_PROP_OVERRIDES += \
    BuildDesc="CPH2661IN-user 15 UKQ1.231108.001 U.R4T2.1d3e97c_2_1 release-keys" \
    BuildFingerprint=OnePlus/CPH2661IN/OP5E93L1:15/UKQ1.231108.001/U.R4T2.1d3e97c_2_1:user/release-keys \
    DeviceName=OP5E93L1 \
    DeviceProduct=CPH2661 \
    SystemDevice=OP5E93L1 \
    SystemName=CPH2661


WITH_GMS := true
TARGET_ENABLE_BLUR := true
TARGET_SUPPORTS_QUICK_TAP := true
TARGET_HAS_UDFPS := true
TARGET_USE_GOOGLE_TELEPHONY := true
TARGET_PREBUILT_PIXEL_LAUNCHER := true
TARGET_FACE_UNLOCK_SUPPORTED := true
RISING_BUILDTYPE := OFFICIAL
TARGET_DISABLE_EPPE := true
TARGET_SHIPS_MATLOG := true
TARGET_DEFAULT_PIXEL_LAUNCHER := true
TARGET_PREBUILT_BCR := true
TARGET_PREBUILT_LAWNCHAIR_LAUNCHER := true
USE_PIXEL_CHARGER := true
TARGET_INCLUDE_LIVE_WALLPAPERS := true
TARGET_SUPPORTS_QUICK_TAP  := true
TARGET_INCLUDE_CARRIER_SETTINGS := true
TARGET_SUPPORTS_NOW_PLAYING := true
TARGET_INCLUDE_CAMERA_GO := true
TARGET_SUPPORTS_GOOGLE_BATTERY := true
TARGET_SUPPORTS_CLEAR_CALLING := true
TARGET_SUPPORTS_GOOGLE_RECORDER := true
TARGET_SUPPORTS_CALL_RECORDING := true
TARGET_SUPPORTS_ADPATIVE_CHARGING := true
TARGET_USES_BLUR_RECENT := true
