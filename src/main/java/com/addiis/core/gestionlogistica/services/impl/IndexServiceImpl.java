package com.addiis.core.gestionlogistica.services.impl;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.services.IndexService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    @Value("${addiis.app.version}")
    private String version;

    @Value("${addiis.app.nombre}")
    private String nombreApp;
    @Override
    public String procesarInicial() {
        AddiisLogger.info("Ejecutando el servicio indice");
        return "AppName= "+nombreApp+"<br>AppVersion="+version;
    }
}
