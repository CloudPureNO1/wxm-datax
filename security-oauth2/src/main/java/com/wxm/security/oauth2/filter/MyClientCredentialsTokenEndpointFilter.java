package com.wxm.security.oauth2.filter;

import com.alibaba.fastjson.JSON;
import com.wxm.security.oauth2.point.MyAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * <p>重写filter实现客户端自定义异常处理</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/6/11 16:05
 * @since 1.0.0
 */
@Slf4j
@Component
public class MyClientCredentialsTokenEndpointFilter extends ClientCredentialsTokenEndpointFilter {
    public MyClientCredentialsTokenEndpointFilter(PasswordEncoder passwordEncoder, ClientDetailsService clientDetailsService, MyAuthenticationEntryPoint myAuthenticationEntryPoint ) {
        super.setAllowOnlyPost(true);
        super.setAuthenticationEntryPoint(myAuthenticationEntryPoint);
        super.setAuthenticationManager(new ClientAuthenticationManager(passwordEncoder, clientDetailsService));

        this.postProcess();
    }

    private void postProcess() {
        super.afterPropertiesSet();
    }

    private static class ClientAuthenticationManager implements AuthenticationManager {

        private final PasswordEncoder passwordEncoder;

        private final ClientDetailsService clientDetailsService;

        public ClientAuthenticationManager(PasswordEncoder passwordEncoder, ClientDetailsService clientDetailsService) {
            this.passwordEncoder = passwordEncoder;
            this.clientDetailsService = clientDetailsService;
        }

        /**
         * @param authentication {"authenticated":false,"authorities":[],"credentials":"client-a-p","name":"client-a","principal":"client-a"}
         * @see AuthenticationManager#authenticate(Authentication)
         */
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            log.debug("Incoming Authentication: {}", JSON.toJSONString(authentication));

            final String clientId = authentication.getName();
            final ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

            if(clientDetails == null|| !StringUtils.hasLength(clientDetails.getClientId())){
                throw new BadCredentialsException("客户端(client_id)有误");
            }
            if (!passwordEncoder.matches((CharSequence) authentication.getCredentials(), clientDetails.getClientSecret())) {
                throw new BadCredentialsException("客户端密钥错误!");
            }

            return new ClientAuthenticationToken(clientDetails);
        }
    }

    private static class ClientAuthenticationToken extends AbstractAuthenticationToken {

        private final Object principal;

        private final Object credentials;

        public ClientAuthenticationToken(ClientDetails clientDetails) {
            super(clientDetails.getAuthorities());
            this.principal = clientDetails.getClientId();
            this.credentials = clientDetails.getClientSecret();
            super.setAuthenticated(true);
        }

        @Override
        public Object getCredentials() {
            return credentials;
        }

        @Override
        public Object getPrincipal() {
            return principal;
        }
    }

}
