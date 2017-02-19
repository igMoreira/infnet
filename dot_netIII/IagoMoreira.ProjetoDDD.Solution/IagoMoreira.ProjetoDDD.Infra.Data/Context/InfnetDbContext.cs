using IagoMoreira.ProjetoDDD.Domain;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.Entity.ModelConfiguration.Conventions;
using IagoMoreira.ProjetoDDD.Infra.Data.Mapping;
using IagoMoreira.ProjetoDDD.Infra.Data.Interfaces;

namespace IagoMoreira.ProjetoDDD.Infra.Data.Context
{
    public class InfnetDbContext : DbContext, IDbContext
    {

        public DbSet<Aluno> Alunos { get; set; }
        public DbSet<Professor> Professores { get; set; }
        public DbSet<Turma> Turmas { get; set; }
        public DbSet<Curso> Cursos { get; set; }
        public DbSet<Sala> Salas { get; set; }
        public DbSet<AlunoCurso> AlunoCursos { get; set; }
        public DbSet<TurmaCurso> TurmaCursos { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Conventions.Remove<PluralizingTableNameConvention>();
            modelBuilder.Conventions.Remove<OneToManyCascadeDeleteConvention>();
            modelBuilder.Conventions.Remove<ManyToManyCascadeDeleteConvention>();

            modelBuilder.Properties<string>().Configure(p => p.HasColumnType("varchar"));
            modelBuilder.Properties<string>().Configure(p => p.HasMaxLength(150));
            modelBuilder.Properties<string>().Where(p => p.Name == p.ReflectedType.Name + "Id").Configure(p => p.IsKey());

            modelBuilder.Configurations.Add(new AlunoMap());
            base.OnModelCreating(modelBuilder);
        }

        IDbSet<T> IDbContext.Set<T>()
        {
            return base.Set<T>();
        }
    }
}
