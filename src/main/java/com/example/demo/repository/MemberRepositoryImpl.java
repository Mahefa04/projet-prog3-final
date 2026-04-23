package com.example.demo.repository;

import com.example.demo.model.Collectivity;
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

            if (member.getGender() != null) {
                pstmt.setString(6, mapGenderToDatabase(member.getGender()));
            } else {
                pstmt.setNull(6, java.sql.Types.VARCHAR);
            }

            pstmt.setString(7, member.getAddress());
            pstmt.setString(8, member.getProfession());

            if (member.getPhoneNumber() != null) {
                pstmt.setString(9, String.valueOf(member.getPhoneNumber()));
            } else {
                pstmt.setNull(9, java.sql.Types.VARCHAR);
            }

            pstmt.setString(10, member.getEmail());

            if (member.getOccupation() != null) {
                pstmt.setString(11, mapOccupationToDatabase(member.getOccupation()));
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
        member.setLastName(rs.getString("last_name"));
        member.setFirstName(rs.getString("first_name"));

        Date birthDate = rs.getDate("birth_date");
        if (birthDate != null) {
            member.setBirthDate(birthDate.toLocalDate());
        }

        member.setGender(mapGenderFromDatabase(rs.getString("gender")));
        member.setAddress(rs.getString("address"));
        member.setProfession(rs.getString("profession"));

        String phoneValue = rs.getString("phone");
        if (phoneValue != null && !phoneValue.trim().isEmpty()) {
            try {
                member.setPhone(rs.getString(phoneValue));
            } catch (NumberFormatException e) {
                member.setPhone(null);
            }
        }

        member.setEmail(rs.getString("email"));
        member.setOccupation(mapOccupationFromDatabase(rs.getString("occupation")));

        String collectivityId = rs.getString("collectivity_id");
        if (collectivityId != null) {
            Collectivity collectivity = new Collectivity();
            collectivity.setId(collectivityId);
            member.setCollectivity(collectivity);
        }

        return member;
    }

    private Gender mapGenderFromDatabase(String value) {
        if (value == null) {
            return null;
        }

        if ("M".equalsIgnoreCase(value)) {
            return Gender.MALE;
        }

        if ("F".equalsIgnoreCase(value)) {
            return Gender.FEMALE;
        }

        return null;
    }

    private String mapGenderToDatabase(Gender gender) {
        if (gender == Gender.MALE) {
            return "M";
        }

        if (gender == Gender.FEMALE) {
            return "F";
        }

        return null;
    }

    private String mapOccupationFromDatabase(String value) {
        if (value == null) {
            return null;
        }

        if ("Président".equalsIgnoreCase(value)) {
            return MemberOccupation.PRESIDENT;
        }
        if ("Vice président".equalsIgnoreCase(value)) {
            return MemberOccupation.VICE_PRESIDENT;
        }
        if ("Secrétaire".equalsIgnoreCase(value)) {
            return MemberOccupation.SECRETARY;
        }
        if ("Trésorier".equalsIgnoreCase(value)) {
            return MemberOccupation.TREASURER;
        }
        if ("Confirmé".equalsIgnoreCase(value)) {
            return MemberOccupation.SENIOR;
        }

        return null;
    }

    private String mapOccupationToDatabase(MemberOccupation occupation) {
        if (occupation == MemberOccupation.PRESIDENT) {
            return "Président";
        }
        if (occupation == MemberOccupation.VICE_PRESIDENT) {
            return "Vice président";
        }
        if (occupation == MemberOccupation.SECRETARY) {
            return "Secrétaire";
        }
        if (occupation == MemberOccupation.TREASURER) {
            return "Trésorier";
        }
        if (occupation == MemberOccupation.SENIOR) {
            return "Confirmé";
        }

        return null;
    }
}