package org.example.dk.cas.events;

import org.apereo.cas.support.events.authentication.CasAuthenticationTransactionFailureEvent;
import org.apereo.cas.web.support.WebUtils;
import org.apereo.inspektr.common.web.ClientInfoHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.webflow.execution.RequestContextHolder;

public class AuthenticationFailureLoggingCasEventListener {

    private static final String AUTHN_TX_FAIL_MSG = "===> Authentication transaction failed for credential with id: [{}]";

    private static final String AUTH_TX_FAIL_DETAILS_HANDLER_USED = "===> Authentication handler(s) used for this transaction: [{}]";

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureLoggingCasEventListener.class);

    @EventListener
    public void logAuthenticationTransactionFailureEvent(CasAuthenticationTransactionFailureEvent e) {
        var clientInfo = ClientInfoHolder.getClientInfo();
        LOGGER.debug(AUTHN_TX_FAIL_MSG, e.getCredential().getId());
        LOGGER.debug(AUTH_TX_FAIL_DETAILS_HANDLER_USED, e.getFailures().keySet());

        e.getFailures().forEach((k, v) -> {
            LOGGER.debug("===> WHAT HAPPENED: " + v);
        });

        LOGGER.debug("===> Client IP: " + clientInfo.getClientIpAddress());

        var requestContext = RequestContextHolder.getRequestContext();
        var service = WebUtils.getService(requestContext);
        if (service != null) {
            LOGGER.debug("===> Attempting to access service: " + service.getId());
        }
    }
}
