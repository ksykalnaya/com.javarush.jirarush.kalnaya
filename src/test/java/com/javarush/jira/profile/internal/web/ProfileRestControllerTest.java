package com.javarush.jira.profile.internal.web;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.profile.internal.ProfileRepository;
import com.javarush.jira.profile.internal.model.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.javarush.jira.login.internal.web.UserTestData.*;
import static com.javarush.jira.profile.internal.web.ProfileTestData.*;
import static com.javarush.jira.common.util.JsonUtil.writeValue;

import static com.javarush.jira.common.BaseHandler.REST_URL;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileRestControllerTest extends AbstractControllerTest {
    public static final String PROFILE_REST_URL = REST_URL + "/profile";

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(PROFILE_REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PROFILE_TO_MATCHER.contentJson(USER_PROFILE_TO));
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void getGuest() throws Exception {
        perform(MockMvcRequestBuilders.get(PROFILE_REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PROFILE_TO_MATCHER.contentJson(GUEST_PROFILE_EMPTY_TO));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(PROFILE_REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        perform(MockMvcRequestBuilders.put(PROFILE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getUpdatedTo())))
                .andDo(print())
                .andExpect(status().isNoContent());

        Profile updatedAfter = profileRepository.getExisted(USER_ID);
        Profile updated = getUpdated(USER_ID);
        assertEquals(updatedAfter.getMailNotifications(), updated.getMailNotifications());
        PROFILE_MATCHER.assertMatch(updatedAfter, updated);
    }

    @Test
    void updateUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.put(PROFILE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getUpdatedTo())))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void update_NewRecord() throws Exception {
        Profile guestProfile = profileRepository.findById(GUEST_ID).orElse(null);
        perform(MockMvcRequestBuilders.put(PROFILE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getNewTo())))
                .andDo(print())
                .andExpect(status().isNoContent());

        Profile createdProfile = profileRepository.getExisted(GUEST_ID);
        Profile created = getNew(GUEST_ID);
        assertNull(guestProfile);
        assertEquals(createdProfile.getMailNotifications(), created.getMailNotifications());
        PROFILE_MATCHER.assertMatch(createdProfile, created);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateInvalid() throws Exception {
        perform(MockMvcRequestBuilders.put(PROFILE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getInvalidTo())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateWithUnknownNotification() throws Exception {
        perform(MockMvcRequestBuilders.put(PROFILE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getWithUnknownNotificationTo())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateWithUnknownContact() throws Exception {
        perform(MockMvcRequestBuilders.put(PROFILE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getWithUnknownContactTo())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateWithContactHtmlUnsafe() throws Exception {
        perform(MockMvcRequestBuilders.put(PROFILE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getWithContactHtmlUnsafeTo())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}