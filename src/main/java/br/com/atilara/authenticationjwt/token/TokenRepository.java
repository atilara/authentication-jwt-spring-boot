package br.com.atilara.authenticationjwt.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenModel, UUID> {

    @Query("""
        select t from TokenModel t inner join UserModel u on t.user.id = u.id
        where u.id = :userId and t.expired = false and t.revoked = false
    """)
    List<TokenModel> findAllValidTokensByUserId(UUID userId);
    
    Optional<TokenModel> findByToken(String token);
    
}
