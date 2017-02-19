using PauloMau.AV2.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PauloMau.AV2.Domain.Context
{
    public class ContextoDb : DbContext
    {

        public DbSet<Aluno> Alunos { get; set; }
        public DbSet<Curso> Cursos { get; set; }
        public DbSet<CursoProfessor> CursoProfessores { get; set; }
        public DbSet<Professor> Professores { get; set; }
        public DbSet<Programa> Programa { get; set; }
        public DbSet<Sala> Salas { get; set; }
        public DbSet<Turma> Turmas { get; set; }
        public DbSet<TurmaAluno> TurmaAlunos { get; set; }
        public DbSet<Endereco> Enderecos { get; set; }

    }
}
