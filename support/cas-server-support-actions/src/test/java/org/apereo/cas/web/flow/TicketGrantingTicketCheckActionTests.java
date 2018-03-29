package org.apereo.cas.web.flow;

import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.AbstractCentralAuthenticationServiceTests;
import org.apereo.cas.authentication.AuthenticationResult;
import org.apereo.cas.authentication.CoreAuthenticationTestUtils;
import org.apereo.cas.mock.MockTicketGrantingTicket;
import org.apereo.cas.ticket.TicketGrantingTicket;
import org.apereo.cas.web.config.CasSupportActionsConfiguration;
import org.apereo.cas.web.flow.login.TicketGrantingTicketCheckAction;
import org.apereo.cas.web.support.WebUtils;
import org.junit.Test;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.test.MockRequestContext;

import static org.junit.Assert.*;

/**
 * Handles tests for {@link TicketGrantingTicketCheckAction}.
 *
 * @author Misagh Moayyed
 * @since 4.1.0
 */
@DirtiesContext
@Import(CasSupportActionsConfiguration.class)
@Slf4j
public class TicketGrantingTicketCheckActionTests extends AbstractCentralAuthenticationServiceTests {

    @Test
    public void verifyNullTicket() throws Exception {
        final var ctx = new MockRequestContext();
        final var action = new
            TicketGrantingTicketCheckAction(this.getCentralAuthenticationService());
        final var event = action.doExecute(ctx);
        assertEquals(TicketGrantingTicketCheckAction.NOT_EXISTS, event.getId());
    }

    @Test
    public void verifyInvalidTicket() throws Exception {

        final var ctx = new MockRequestContext();
        final var tgt = new MockTicketGrantingTicket("user");

        WebUtils.putTicketGrantingTicketInScopes(ctx, tgt);
        final var action = new
            TicketGrantingTicketCheckAction(this.getCentralAuthenticationService());
        final var event = action.doExecute(ctx);
        assertEquals(TicketGrantingTicketCheckAction.INVALID, event.getId());
    }

    @Test
    public void verifyValidTicket() throws Exception {

        final var ctx = new MockRequestContext();
        final var ctxAuthN = CoreAuthenticationTestUtils.getAuthenticationResult(getAuthenticationSystemSupport());

        final var tgt = this.getCentralAuthenticationService().createTicketGrantingTicket(ctxAuthN);

        WebUtils.putTicketGrantingTicketInScopes(ctx, tgt);
        final var action = new
            TicketGrantingTicketCheckAction(this.getCentralAuthenticationService());
        final var event = action.doExecute(ctx);
        assertEquals(TicketGrantingTicketCheckAction.VALID, event.getId());
    }


}
