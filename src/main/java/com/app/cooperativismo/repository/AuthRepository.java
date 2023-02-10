package com.app.cooperativismo.repository;

import com.app.cooperativismo.dto.LoginDTO;
import java.util.concurrent.CompletionStage;

public interface AuthRepository {
    CompletionStage<LoginDTO> login(LoginDTO bean);
}
