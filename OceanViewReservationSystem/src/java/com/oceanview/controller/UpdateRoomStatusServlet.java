package com.oceanview.controller;

import com.oceanview.dao.RoomDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateRoomStatus")
public class UpdateRoomStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        try {
            String roomIdStr = req.getParameter("roomId");
            String status = req.getParameter("status");

            if (roomIdStr == null || status == null ||
                roomIdStr.isBlank() || status.isBlank()) {

                resp.sendRedirect("checkRooms.html?error=true");
                return;
            }

            int roomId = Integer.parseInt(roomIdStr);

            roomDAO.updateRoomStatus(roomId, status);


            resp.sendRedirect("checkRooms.html?success=true");

        } catch (Exception e) {
            e.printStackTrace();

            resp.sendRedirect("checkRooms.html?error=true");
        }
    }
}
