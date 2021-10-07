#
# Copyright (C) 2024 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit_only.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit some common Lineage stuff.
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

# Inherit from peridot device
$(call inherit-product, device/xiaomi/peridot/device.mk)

PRODUCT_NAME := lineage_peridot
PRODUCT_DEVICE := peridot
PRODUCT_MANUFACTURER := Xiaomi
PRODUCT_BRAND := POCO
PRODUCT_MODEL := 24069PC21G

PRODUCT_SYSTEM_NAME := peridot_global
PRODUCT_SYSTEM_DEVICE := peridot

PRODUCT_BUILD_PROP_OVERRIDES += \
    PRIVATE_BUILD_DESC="peridot_global-user 14 UKQ1.240116.001 V816.0.5.0.UNPMIXM release-keys" \
    TARGET_DEVICE=$(PRODUCT_SYSTEM_DEVICE) \
    TARGET_PRODUCT=$(PRODUCT_SYSTEM_NAME)

BUILD_FINGERPRINT := POCO/peridot_global/peridot:14/UKQ1.240116.001/V816.0.5.0.UNPMIXM:user/release-keys

PRODUCT_GMS_CLIENTID_BASE := android-xiaomi


# RisingOS Flags
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
TARGET_CALL_RECORDING_SUPPORTED := true
TENX_BUILD_TYPE := Official
TARGET_USE_CUSTOM_PACKAGE_INSTALLER := true
TARGET_SUPPORTS_GOOGLE_RECORDER := true

# Add Official Stuff
PRODUCT_BUILD_PROP_OVERRIDES += \
    RISING_CHIPSET="Qualcomm Snapdragon SM8635" \
    RISING_MAINTAINER="BASUBHAJANTRI"
