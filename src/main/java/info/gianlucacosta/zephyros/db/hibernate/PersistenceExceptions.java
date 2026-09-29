package info.gianlucacosta.zephyros.db.hibernate;

import info.gianlucacosta.zephyros.db.sql.SqlState;
import org.hibernate.JDBCException;

import javax.persistence.PersistenceException;
import java.util.Optional;


public interface PersistenceExceptions {
    /**
     * Analyzes the given PersistenceException and tries to return its SQL state
     * as an SqlState enum value.
     *
     * @param persistenceException The PersistenceException to analyze
     * @return The SqlState, if its code is found and known; empty otherwise.
     */
    static Optional<SqlState> getSqlState(PersistenceException persistenceException) {
        if (persistenceException.getCause() instanceof JDBCException) {
            JDBCException jdbcException =
                    (JDBCException) persistenceException.getCause();

            return SqlState.getByCode(jdbcException.getSQLState());
        }

        return Optional.empty();
    }
}
