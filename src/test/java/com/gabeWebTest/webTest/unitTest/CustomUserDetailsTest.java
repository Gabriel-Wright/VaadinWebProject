package com.gabeWebTest.webTest.unitTest;

import com.gabeWebTest.webTest.data.users.CustomUserDetails;
import com.gabeWebTest.webTest.data.users.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.helger.commons.mock.CommonsAssert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomUserDetailsTest {

    @Test
    public void testGetAuthorities() {
        User mockUser = mock(User.class);
        when(mockUser.getRole()).thenReturn("ADMIN");

        CustomUserDetails customUserDetails = new CustomUserDetails(mockUser);

        // Get the authorities from the CustomUserDetails instance
        var authorities = customUserDetails.getAuthorities();

        // Verify that the authorities contain the expected role
        assertEquals(1, authorities.size());
        assertEquals(new SimpleGrantedAuthority("ADMIN"), authorities.iterator().next());

    }
}
