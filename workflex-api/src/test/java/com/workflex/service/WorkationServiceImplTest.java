package com.workflex.service;

import com.workflex.api.dto.UpdateWorkationRequest;
import com.workflex.api.dto.WorkationSearchParams;
import com.workflex.domain.Workation;
import com.workflex.mapper.WorkationEntityMapper;
import com.workflex.persistence.WorkationEntity;
import com.workflex.persistence.WorkationRepository;
import jakarta.persistence.EntityNotFoundException;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Test
    void update_shouldReturnUpdatedWorkation() {
        // arrange
        WorkationEntity entity = new WorkationEntity();
        entity.setDestinationCountry("germany");
        entity.setStartDate(LocalDate.of(2026, 1, 1));
        entity.setEndDate(LocalDate.of(2026, 1, 10));

        when(repository.findByIdAndDeletedAtIsNull(1L))
                .thenReturn(Optional.of(entity));

        Workation domain = Workation.create(
                "john",
                "ITALY",
                "FRANCE",
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 10)
        );

        when(mapper.toDomain(entity)).thenReturn(domain);

        // act
        Workation result = service.update(
                1L,
                UpdateWorkationRequest.builder()
                        .destinationCountry("FRANCE")
                        .startDate(LocalDate.of(2026, 1, 1))
                        .endDate(LocalDate.of(2026, 1, 10))
                        .build()
        );

        // assert
        assertThat(result).isNotNull();
        assertThat(entity.getDestinationCountry()).isEqualTo("FRANCE");

        verify(repository).findByIdAndDeletedAtIsNull(1L);
        verify(mapper).toDomain(entity);
    }

    @Test
    void update_shouldReturnExceptionWhenWorkationDoesNotExist() {
        when(repository.findByIdAndDeletedAtIsNull(100L)).thenReturn(Optional.empty());

        UpdateWorkationRequest request =
                UpdateWorkationRequest.builder()
                        .destinationCountry("FRANCE")
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now().plusDays(5))
                        .build();

        assertThatThrownBy(() -> service.update(100L, request))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Workation not found");
    }

    @Test
    void getById_shouldReturnWorkationById() {
        WorkationEntity entity = new WorkationEntity();
        entity.setDestinationCountry("germany");
        entity.setStartDate(LocalDate.of(2026, 1, 1));
        entity.setEndDate(LocalDate.of(2026, 1, 10));

        when(repository.findByIdAndDeletedAtIsNull(1L))
                .thenReturn(Optional.of(entity));

        Workation domain = Workation.create(
                "john",
                "ITALY",
                "germany",
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 10)
        );

        when(mapper.toDomain(entity)).thenReturn(domain);

        Workation result = service.getById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getDestinationCountry()).isEqualTo("germany");
    }
}
