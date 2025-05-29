package tech.dekar.shared.domain.repository

import tech.dekar.shared.domain.model.DbDataModel

/**
 * A generic interface for repositories.
 *
 * @param T the type of data the repository handles.
 */
abstract class Repository<Q, T : DbDataModel> {
    abstract fun insert(item: T): Result<Long>
    /**
     * Converts a query result to a model.
     *
     * @param queryResult the query result to convert.
     * @return the converted model.
     */
    protected abstract fun toModel(queryResult: Q): T
}