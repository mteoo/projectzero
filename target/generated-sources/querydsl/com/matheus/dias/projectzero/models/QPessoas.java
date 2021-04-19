package com.matheus.dias.projectzero.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPessoas is a Querydsl query type for Pessoas
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPessoas extends EntityPathBase<Pessoas> {

    private static final long serialVersionUID = -112118900L;

    public static final QPessoas pessoas = new QPessoas("pessoas");

    public final DateTimePath<java.time.LocalDateTime> audDhAlteracao = createDateTime("audDhAlteracao", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> audDhCriacao = createDateTime("audDhCriacao", java.time.LocalDateTime.class);

    public final NumberPath<Integer> audVersao = createNumber("audVersao", Integer.class);

    public final StringPath cpf = createString("cpf");

    public final DatePath<java.time.LocalDate> dtNascimento = createDate("dtNascimento", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nacionalidade = createString("nacionalidade");

    public final StringPath naturalidade = createString("naturalidade");

    public final StringPath nome = createString("nome");

    public QPessoas(String variable) {
        super(Pessoas.class, forVariable(variable));
    }

    public QPessoas(Path<? extends Pessoas> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPessoas(PathMetadata metadata) {
        super(Pessoas.class, metadata);
    }

}

