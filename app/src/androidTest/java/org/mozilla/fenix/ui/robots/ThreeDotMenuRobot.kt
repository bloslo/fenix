/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

@file:Suppress("TooManyFunctions")

package org.mozilla.fenix.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.Matchers.allOf
import org.mozilla.fenix.R
import org.mozilla.fenix.helpers.TestAssetHelper.waitingTime
import org.mozilla.fenix.helpers.click

/**
 * Implementation of Robot Pattern for the three dot (main) menu.
 */
class ThreeDotMenuRobot {
    fun verifySettingsButton() = assertSettingsButton()
    fun verifyLibraryButton() = assertLibraryButton()
    fun verifyHelpButton() = assertHelpButton()
    fun verifyThreeDotMenuExists() = threeDotMenuRecyclerViewExists()
    fun verifyForwardButton() = assertForwardButton()
    fun verifyBackButton() = assertBackButton()
    fun verifyRefreshButton() = assertRefreshButton()

    class Transition {

        private val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        fun openSettings(interact: SettingsRobot.() -> Unit): SettingsRobot.Transition {
            mDevice.wait(Until.findObject(By.text("Settings")), waitingTime)
            settingsButton().click()

            SettingsRobot().interact()
            return SettingsRobot.Transition()
        }

        fun openLibrary(interact: LibraryRobot.() -> Unit): LibraryRobot.Transition {
            mDevice.wait(Until.findObject(By.text("Library")), waitingTime)
            libraryButton().click()

            LibraryRobot().interact()
            return LibraryRobot.Transition()
        }

        fun openHelp(interact: BrowserRobot.() -> Unit): BrowserRobot.Transition {
            mDevice.wait(Until.findObject(By.text("Help")), waitingTime)
            helpButton().click()

            BrowserRobot().interact()
            return BrowserRobot.Transition()
        }

        fun goForward(interact: BrowserRobot.() -> Unit): BrowserRobot.Transition {
            mDevice.wait(Until.findObject(By.desc("Forward")), waitingTime)
            forwardButton().click()

            BrowserRobot().interact()
            return BrowserRobot.Transition()
        }

        fun goBack(interact: BrowserRobot.() -> Unit): BrowserRobot.Transition {
            mDevice.wait(Until.findObject(By.desc("Back")), waitingTime)
            backButton().click()

            BrowserRobot().interact()
            return BrowserRobot.Transition()
        }

        fun refreshPage(interact: BrowserRobot.() -> Unit): BrowserRobot.Transition {
            mDevice.wait(Until.findObject(By.desc("Refresh")), waitingTime)
            refreshButton().click()

            BrowserRobot().interact()
            return BrowserRobot.Transition()
        }
    }
}
private fun threeDotMenuRecyclerViewExists() {
    onView(withId(R.id.mozac_browser_menu_recyclerView)).check(matches(isDisplayed()))
}
private fun settingsButton() = onView(allOf(withText("Settings")))
private fun assertSettingsButton() = settingsButton()
    .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

private fun libraryButton() = onView(allOf(withText(R.string.browser_menu_your_library)))
private fun assertLibraryButton() = libraryButton()
    .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

private fun helpButton() = onView(allOf(withText(R.string.browser_menu_help)))
private fun assertHelpButton() = helpButton()
    .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

private fun forwardButton() = onView(ViewMatchers.withContentDescription("Forward"))
private fun assertForwardButton() = forwardButton()
    .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

private fun backButton() = onView(ViewMatchers.withContentDescription("Back"))
private fun assertBackButton() = backButton()
    .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

private fun refreshButton() = onView(ViewMatchers.withContentDescription("Refresh"))
private fun assertRefreshButton() = refreshButton()
    .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
