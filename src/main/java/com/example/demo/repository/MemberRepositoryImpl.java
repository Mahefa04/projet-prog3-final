package com.example.demo.repository;

import com.example.demo.model.Gender;
import com.example.demo.model.Member;
import com.example.demo.model.MemberOccupation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepository {

    private final Connection connection;

    public MemberRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Member findById(String id) {
        String sql = "SELECT * FROM members WHERE id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();

            Member member = null;

            if (rs.next()) {
                member = mapResultSetToMember(rs);
            }

            rs.close();
            pstmt.close();

            return member;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding member by id", e);
        }
    }

    @Override
    public Member save(Member member) {
        String sql = "INSERT INTO members (id, collectivity_id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getId());
            pstmt.setString(3, member.getFirstName());
            pstmt.setString(4, member.getLastName());

            if (member.getBirthDate() != null) {
                pstmt.setDate(5, Date.valueOf(member.getBirthDate()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }

            if (member.getGender() != null) {
                pstmt.setString(6, member.getGender().name());
            } else {
                pstmt.setNull(6, java.sql.Types.VARCHAR);
            }

            pstmt.setString(7, member.getAddress());
            pstmt.setString(8, member.getProfession());

            if (member.getPhoneNumber() != null) {
                pstmt.setInt(9, member.getPhoneNumber());
            } else {
                pstmt.setNull(9, java.sql.Types.INTEGER);
            }

            pstmt.setString(10, member.getEmail());

            if (member.getOccupation() != null) {
                pstmt.setString(11, member.getOccupation().name());
            } else {
                pstmt.setNull(11, java.sql.Types.VARCHAR);
            }

            pstmt.executeUpdate();
            pstmt.close();

            return member;

        } catch (SQLException e) {
            throw new RuntimeException("Error while saving member", e);
        }
    }

    @Override
    public List<Member> findByCollectivityId(String collectivityId) {
        String sql = "SELECT * FROM members WHERE collectivity_id = ?";
        List<Member> list = new ArrayList<Member>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, collectivityId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Member member = mapResultSetToMember(rs);
                list.add(member);
            }

            rs.close();
            pstmt.close();

            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Error while finding members by collectivityId", e);
        }
    }

    private Member mapResultSetToMember(ResultSet rs) throws SQLException {
        Member member = new Member();

        member.setId(rs.getString("id"));
        member.setId(rs.getString("collectivity_id"));
        member.setFirstName(rs.getString("first_name"));
        member.setLastName(rs.getString("last_name"));

        Date birthDate = rs.getDate("birth_date");
        if (birthDate != null) {
            member.setBirthDate(birthDate.toLocalDate());
        }

        String gender = rs.getString("gender");
        if (gender != null) {
            member.setGender(Gender.valueOf(gender));
        }

        member.setAddress(rs.getString("address"));
        member.setProfession(rs.getString("profession"));

        int phone = rs.getInt("phone_number");
        if (!rs.wasNull()) {
            member.setPhoneNumber(phone);
        }

        member.setEmail(rs.getString("email"));

        String occupationValue = rs.getString("occupation");
        if (occupationValue != null) {
            member.setOccupation(MemberOccupation.valueOf(occupationValue));
        }

        return member;
    }
}