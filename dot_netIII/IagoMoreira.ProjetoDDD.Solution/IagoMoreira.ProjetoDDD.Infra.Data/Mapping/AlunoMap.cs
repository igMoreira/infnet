using IagoMoreira.ProjetoDDD.Domain;
using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Infra.Data.Mapping
{
    public class AlunoMap : EntityTypeConfiguration<Aluno>
    {
        public AlunoMap()
        {
            this.ToTable("Aluno");

            this.HasKey(t => t.AlunoId);

            this.Property(t => t.Nome).IsRequired();

            this.Property(t => t.CPF).IsRequired().HasColumnType("char")
                .IsFixedLength().HasMaxLength(11);

            this.Property(t => t.DataNascimento).HasColumnType("date");
        }
    }
}
