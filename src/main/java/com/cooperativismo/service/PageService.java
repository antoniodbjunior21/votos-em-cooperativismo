package com.cooperativismo.service;

import com.cooperativismo.dto.LoginDTO;
import com.cooperativismo.dto.view.LoginPage;
import com.cooperativismo.dto.view.components.Page;

public interface PageService {
    LoginPage getLoginPage();
    Page authenticateAndGetPageResponse(LoginDTO loginDTO);
}
