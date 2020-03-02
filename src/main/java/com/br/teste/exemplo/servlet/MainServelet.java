/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.teste.exemplo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vitcl
 */
@WebServlet("/")
public class MainServelet extends HttpServlet {

    private List<String> nomes = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8' />");
        out.println("<title>Add and remove</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<form  method='POST'>");
        out.println("<input type='hidden' name='acao' value='salvar' />");
        out.println("<label>");
        out.println("Nome:");
        out.println("</label>");
        if (req.getParameter("nomeEditar") != null
                && req.getParameter("acao").equals("editar") && req.getParameter("index") != null) {
            out.println("<input type='text' name='nomeEditar' placeholder='' value='"
                    + req.getParameter("nomeEditar") + "' />");
            out.println("<input type='hidden' name='index' placeholder='' value='"
                    + req.getParameter("index") + "' />");
            out.println("<button type='submit'>Add</button>");
            out.println("</form>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>");
            out.println("Nome:");
            out.println("</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            for (int i = 0; i < nomes.size(); i++) {
                out.println("<tr>");
                out.println("<td>");
                out.println(nomes.get(i));
                out.println("</td>");
                out.println("<td>");
                out.println("<form  method='POST'>");
                out.println("<input type='hidden' name='acao' value='remover' />");
                out.println("<input type='hidden' name='nomeRemove' value='" + nomes.get(i) + "' />");
                out.println("<button type='submit'>Remover!</button>");
                out.println("</form>");
                out.println("</td>");
                out.println("<td>");
                out.println("<form  method='POST'>");
                out.println("<input type='hidden' name='acao' value='editar' />");
                out.println("<input type='hidden' name='nomeEditar' value='" + nomes.get(i) + "' />");
                out.println("<input type='hidden' name='index' value='" + i + "' />");
                out.println("<button type='submit'>Editar!</button>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } else {
            out.println("<input type='text' name='nome' placeholder='informe um nome' />");
            out.println("<input type='hidden' name='index' value='0' />");
            out.println("<button type='submit'>Add</button>");
            out.println("</form>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>");
            out.println("Nome:");
            out.println("</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            for (int i = 0; i < nomes.size(); i++) {
                out.println("<tr>");
                out.println("<td>");
                out.println(nomes.get(i));
                out.println("</td>");
                out.println("<td>");
                out.println("<form  method='POST'>");
                out.println("<input type='hidden' name='acao' value='remover' />");
                out.println("<input type='hidden' name='nomeRemove' value='" + nomes.get(i) + "' />");
                out.println("<input type='hidden' name='index' value='" + i + "' />");
                out.println("<button type='submit'>Remover!</button>");
                out.println("</form>");
                out.println("</td>");
                out.println("<td>");
                out.println("<form  method='POST'>");
                out.println("<input type='hidden' name='acao' value='editar' />");
                out.println("<input type='hidden' name='nomeEditar' value='" + nomes.get(i) + "' />");
                out.println("<input type='hidden' name='index' value='" + i + "' />");
                out.println("<button type='submit'>Editar!</button>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    /**
     * Método responsável por add um nome e remover um nome da lista
     *
     * @param req => request
     * @param resp => response
     *
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nome = null;

        String nomeEditar = null;

        nome = req.getParameter("nome");

        nomeEditar = req.getParameter("nomeEditar");

        String acao = req.getParameter("acao");
        
        int index = 0;

        index = Integer.parseInt(req.getParameter("index"));

        if (acao.equals("salvar")) {

            if (nome == null) {

                resp.sendRedirect(req.getContextPath() + "/");

            } else {

                nomes.add(nome);

                resp.sendRedirect(req.getContextPath() + "/");

                return;

            }

            if (nomeEditar == null) {

                resp.sendRedirect(req.getContextPath() + "/");
                
            } else {

                nomes.add(index, nomeEditar);
                
                nomes.remove(index + 1);
            }

        }

        if (acao.equals("remover")) {

            doDelete(req, resp);

        }

        if (acao.equals("editar")) {

            doPut(req, resp);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeRemove = req.getParameter("nomeRemove");
        nomes.remove(nomeRemove);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeEditar = req.getParameter("nomeEditar");
        String index = req.getParameter("index");

        if (nomes.get(Integer.parseInt(index)).equals(nomeEditar)) {

            doGet(req, resp);

        }

    }

}
