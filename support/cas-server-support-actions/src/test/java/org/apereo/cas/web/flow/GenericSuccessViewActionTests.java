package org.apereo.cas.web.flow;

import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.CentralAuthenticationService;
import org.apereo.cas.authentication.Authentication;
import org.apereo.cas.authentication.CoreAuthenticationTestUtils;
import org.apereo.cas.authentication.principal.NullPrincipal;
import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.authentication.principal.ServiceFactory;
import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.ticket.InvalidTicketException;
import org.apereo.cas.ticket.TicketGrantingTicket;
import org.apereo.cas.web.flow.login.GenericSuccessViewAction;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link GenericSuccessViewAction}
 * @author Misagh Moayyed
 * @since 4.1.0
 */
@Slf4j
public class GenericSuccessViewActionTests {

    @Test
    public void verifyValidPrincipal() throws InvalidTicketException {
        final var cas = mock(CentralAuthenticationService.class);
        final var mgr = mock(ServicesManager.class);
        final var factory = mock(ServiceFactory.class);
        
        final var authn = mock(Authentication.class);
        when(authn.getPrincipal()).thenReturn(
                CoreAuthenticationTestUtils.getPrincipal("cas"));
        final var tgt = mock(TicketGrantingTicket.class);
        when(tgt.getAuthentication()).thenReturn(authn);
        
        when(cas.getTicket(any(String.class), any())).thenReturn(tgt);
        final var action = new GenericSuccessViewAction(cas, mgr, factory, "");
        final var p = action.getAuthenticationPrincipal("TGT-1");
        assertNotNull(p);
        assertEquals("cas", p.getId());
    }

    @Test
    public void verifyPrincipalCanNotBeDetermined() throws InvalidTicketException {
        final var cas = mock(CentralAuthenticationService.class);
        final var mgr = mock(ServicesManager.class);
        final var factory = mock(ServiceFactory.class);
        when(cas.getTicket(any(String.class), any())).thenThrow(new InvalidTicketException("TGT-1"));
        final var action = new GenericSuccessViewAction(cas, mgr, factory, "");
        final var p = action.getAuthenticationPrincipal("TGT-1");
        assertNotNull(p);
        assertTrue(p instanceof NullPrincipal);
    }
}
