package com.example.admin.contactsapp.view.contact;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.admin.contactsapp.R;
import com.example.admin.contactsapp.model.Contact;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;

public class ContactFragmentTest {
    Contact contact = new Contact();
    ContactFragment contactFragment = new ContactFragment();
    private TestActivityFragments mActivity = null;
    RelativeLayout relativeLayout = (RelativeLayout) mActivity.findViewById(R.id.test_container);

    public static final String PACKAGE_NAME = "com.example.admin.testing";

    @Rule
    public IntentsTestRule<TestActivityFragments> activityIntentsTestRule = new IntentsTestRule<>(TestActivityFragments.class);



    @Before
    public void setUp() throws Exception {
        mActivity = activityIntentsTestRule.getActivity();
        contact = mock(Contact.class);
        contact.setFirstName("Rodrigo");
        contact.setLastName("Chavez");
        contact.setCompany("Mobile");
        contact.setPhoneNumber("5544734136");
        contact.setPhoto(null);
        contact.setPic("https://img00.deviantart.net/2afd/i/2017/287/c/9/goku_ultra_instinct__migatte_no_gokui__by_trebolok-dbql911.jpg");
    }

    @Test
    public  void testLaunchFragment(){
        Assert.assertNotNull(relativeLayout);
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction().add(relativeLayout.getId(),contactFragment).commitAllowingStateLoss();
        getInstrumentation().waitForIdleSync();
        View view = contactFragment.getView().findViewById(R.id.view_in_fragment);
        Assert.assertNotNull(view);

    }

    @Test
    public void testing_addition_contacts(){
        Assert.assertNotNull(relativeLayout);
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction().add(relativeLayout.getId(),contactFragment).commitAllowingStateLoss();
        getInstrumentation().waitForIdleSync();
        onView(withId(R.id.etFirstName))
                .perform(typeText(String.valueOf(contact.getFirstName())), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.etLastName))
                .perform(typeText(String.valueOf(String.valueOf(contact.getLastName()))), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.etCompany))
                .perform(typeText(String.valueOf(String.valueOf(contact.getCompany()))), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.etPhone))
                .perform(typeText(String.valueOf(String.valueOf(contact.getPhoneNumber()))), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.btnSave)).perform(click());

    }

    @Test
    public void testing_addition_contacts_mockito() {
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}