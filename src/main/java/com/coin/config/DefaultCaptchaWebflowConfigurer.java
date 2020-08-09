package com.coin.config;

import com.coin.RememberMeUsernamePasswordCaptchaCredential;
import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.authentication.PolicyBasedAuthenticationManager;
import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConstants;
import org.apereo.cas.web.flow.configurer.DefaultLoginWebflowConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.ViewState;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

/**
 * @ClassName DefaultCaptchaWebflowConfigurer
 * @Description: TODO
 * @Author kh
 * @Date 2020-08-07 15:13
 * @Version V1.0
 **/
@Slf4j
public class DefaultCaptchaWebflowConfigurer extends DefaultLoginWebflowConfigurer {
    public DefaultCaptchaWebflowConfigurer(FlowBuilderServices flowBuilderServices,
                                           FlowDefinitionRegistry flowDefinitionRegistry,
                                           ApplicationContext applicationContext,
                                           CasConfigurationProperties casProperties) {
        super(flowBuilderServices, flowDefinitionRegistry, applicationContext, casProperties);
    }

    @Override
    protected void createRememberMeAuthnWebflowConfig(Flow flow) {
        if (this.casProperties.getTicket().getTgt().getRememberMe().isEnabled()) {
            this.createFlowVariable(flow, "credential", RememberMeUsernamePasswordCaptchaCredential.class);
            ViewState state = (ViewState)this.getState(flow, "viewLoginForm", ViewState.class);
            BinderConfiguration cfg = this.getViewStateBinderConfiguration(state);
            cfg.addBinding(new BinderConfiguration.Binding("rememberMe", (String)null, false));
            cfg.addBinding(new BinderConfiguration.Binding("captcha", (String)null, true));
        } else {
            this.createFlowVariable(flow, "credential", UsernamePasswordCredential.class);
        }
    }
}
