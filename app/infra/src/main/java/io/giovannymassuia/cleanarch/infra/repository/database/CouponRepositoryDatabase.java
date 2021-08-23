package io.giovannymassuia.cleanarch.infra.repository.database;

import io.giovannymassuia.cleanarch.core.domain.entity.Coupon;
import io.giovannymassuia.cleanarch.core.domain.repository.CouponRepository;
import io.giovannymassuia.cleanarch.infra.database.Database;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

public class CouponRepositoryDatabase implements CouponRepository {

    private final Database database;

    public CouponRepositoryDatabase(Database database) {
        this.database = database;
    }

    @Override
    public Optional<Coupon> getByCode(String code) {
        return database.queryOne("select * from coupon where code = :code", Map.of("code", code))
                .map(result -> new Coupon(
                        (String) result.get("code"),
                        (BigDecimal) result.get("percentage"),
                        ((Timestamp) result.get("expire_date")).toLocalDateTime().toLocalDate()
                ));
    }

}
