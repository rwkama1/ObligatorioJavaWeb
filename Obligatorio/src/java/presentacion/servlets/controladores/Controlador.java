/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


abstract class Controlador extends HttpServlet {
    
    protected void despacharMetodoAccion(HttpServletRequest request, HttpServletResponse response)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String accion = request.getParameter("accion") != null ? request.getParameter("accion").toLowerCase() : "index";
        String metodoRequest = request.getMethod().toLowerCase();
        String nombreMetodoAccion = accion + "_" + metodoRequest;
        
        Method metodoAccion = this.getClass().getMethod(nombreMetodoAccion, HttpServletRequest.class, HttpServletResponse.class);
        metodoAccion.invoke(this, request, response);
    }
    
    protected void cargarMensaje(String mensaje, HttpServletRequest request) {
        request.setAttribute("mensaje", mensaje);
    }
    
    protected void cargarMensaje(String mensaje, HttpSession session) {
        session.setAttribute("mensaje", mensaje);
    }
    
    protected void agregarMensajeSesionAMensaje(HttpServletRequest request) {
        String mensajeSesion = (String)request.getSession().getAttribute("mensaje");
        
        if (mensajeSesion != null) {
            String mensaje = (String)request.getAttribute("mensaje");
            
            if (mensaje == null) {
                cargarMensaje(mensajeSesion, request);
            } else {
                cargarMensaje(mensajeSesion + "<br /><br />" + mensaje, request);
            }
            
            request.getSession().removeAttribute("mensaje");
        }
    }
    
    protected void mostrarVista(String vista, HttpServletRequest request, HttpServletResponse response) {
        agregarMensajeSesionAMensaje(request);
        
        try {
            String nombreCarpetaVista = this.getClass().getSimpleName().replaceFirst("Controlador", "");
            
            RequestDispatcher despachador = request.getRequestDispatcher("WEB-INF/vistas/" + nombreCarpetaVista + "/" + vista + ".jsp");
            
            if (despachador != null) {
                despachador.forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo mostrar la vista " + vista + ".");
        }
    }
    
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("index", request, response);
    }
    
    protected void procesarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            despacharMetodoAccion(request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            System.out.println("¡ERROR! No se pudo despachar el método de la acción solicitada.");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        procesarPedido(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        procesarPedido(req, resp);
    }
    
}
