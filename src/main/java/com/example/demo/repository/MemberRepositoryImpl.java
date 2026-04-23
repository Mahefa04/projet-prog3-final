package com.example.demo.repository;

import com.example.demo.model.Collectivity;
import com.example.demo.model.Member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepository {

    private final Connection connection;
    private final Member member;

    public MemberRepositoryImpl(Connection connection, Member member) {
        this.connection = connection;
        this.member = member;
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
        String sql = "INSERT INTO members (id, collectivity_id, last_name, first_name, birth_date, gender, address, profession, phone, email, occupation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, member.getId());

            if (member.getCollectivity() != null) {
                pstmt.setString(2, member.getCollectivity().getId());
            } else {
                pstmt.setNull(2, java.sql.Types.VARCHAR);
            }

            pstmt.setString(3, member.getLastName());
            pstmt.setString(4, member.getFirstName());

            if (member.getBirthDate() != null) {
                pstmt.setDate(5, Date.valueOf(member.getBirthDate()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }

            pstmt.setString(6, member.getGender());
            pstmt.setString(7, member.getAddress());
            pstmt.setString(8, member.getProfession());
            pstmt.setString(9, member.getPhone());
            pstmt.setString(10, member.getEmail());
            member.setOccupation(11, member.getOccupation("occupation"));

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
                list.add(mapResultSetToMember(rs));
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
        member.setLastName(rs.getString("last_name"));
        member.setFirstName(rs.getString("first_name"));

        Date birthDate = rs.getDate("birth_date");
        if (birthDate != null) {
            member.setBirthDate(birthDate.toLocalDate());
        }

        member.setGender(rs.getString("gender"));
        member.setAddress(rs.getString("address"));
        member.setProfession(rs.getString("profession"));
        member.setPhone(rs.getString("phone"));
        member.setEmail(rs.getString("email"));
        member.setOccupation(11, member.getOccupation("occupation"));

        Collectivity collectivity = new Collectivity();
        collectivity.setId(rs.getString("collectivity_id"));
        member.setCollectivity(collectivity);

        return member;
    }
}