package com.workflex.service;

import com.workflex.api.dto.WorkationSearchParams;
import com.workflex.domain.Workation;
import com.workflex.mapper.WorkationEntityMapper;
import com.workflex.persistence.WorkationEntity;
import com.workflex.persistence.WorkationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WorkationServiceImplTest {

    private WorkationServiceImpl service;

    @Mock
    private WorkationRepository repository;

    @Mock
    private WorkationEntityMapper mapper;

    @BeforeEach
    void setUp() {
        service = new WorkationServiceImpl(repository, mapper);
    }

    @Test
    void search_shouldReturnWorkationsPagedWorkation() {

        Pageable pageable = PageRequest.of(0, 10);

        WorkationSearchParams params = new WorkationSearchParams();
        params.setEmployee("john");

        WorkationEntity entity = new WorkationEntity();
        entity.setEmployee("john");
        entity.setOriginCountry("ITALY");

        Page<WorkationEntity> entityPage =
                new PageImpl<>(
                        List.of(entity),
                        pageable, 1);

        Workation domain = Workation.create(
                "john", "ITALY", "Germany", LocalDate.now(), LocalDate.now()
        );

        when(repository.search(
                eq("john"),
                isNull(),
                eq(pageable)
        )).thenReturn(entityPage);

        when(mapper.toDomain(entity)).thenReturn(domain);

        Page<Workation> response = service.search(params, pageable);

        assertThat(response)
                .hasSize(1)
                .first()
                .satisfies(res -> {
                    assertThat(res.getEmployee()).isEqualTo("john");
                });

        verify(repository).search(eq("john"), isNull(), eq(pageable));
        verify(mapper).toDomain(entity);
    }
}
