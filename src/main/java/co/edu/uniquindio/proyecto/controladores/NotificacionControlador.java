package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.servicios.NotificacionServicio;
import co.edu.uniquindio.proyecto.servicios.impl.NotificacionServicioImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
public class NotificacionControlador {

    private final NotificacionServicioImpl notificacionServicio;

    public NotificacionControlador(NotificacionServicioImpl notificacionServicio) {
        this.notificacionServicio = notificacionServicio;
    }

    @PostMapping("/notificar")
    @Operation(summary = "Enviar una notificación", description = "Envía una notificación a los usuarios conectados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación enviada correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public String enviarNotificacion(@RequestBody Reporte reporte) {
        notificacionServicio.enviarNotificacion(reporte);
        return "Notificación enviada correctamente.";
    }
}

