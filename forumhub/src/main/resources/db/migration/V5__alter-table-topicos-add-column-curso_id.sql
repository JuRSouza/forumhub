ALTER TABLE topicos ADD COLUMN curso_id BIGINT;

ALTER TABLE topicos ADD CONSTRAINT fk_topico_curso
FOREIGN KEY (curso_id) REFERENCES cursos(id);
