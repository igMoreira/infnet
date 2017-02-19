using PauloMau.AV2.Domain.Context;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;

namespace PauloMau.AV2.Domain.Entities
{
    public class Sala
    {
        [ScaffoldColumn(false)]
        [Bindable(false)]
        [ReadOnly(true)]
        public int SalaId { get; set; }

        [DisplayName("Código")]
        [ReadOnly(true)]
        [StringLength(5)]
        [Required]
        public string Codigo { get; set; }

        [DisplayName("Nome")]
        [StringLength(30)]
        public string Nome { get; set; }

        [ReadOnly(true)]
        [DisplayName("Total de Alunos")]
        public int TotalAlunos {
            get {
                ContextoDb db = new ContextoDb();
                var turmas = from t in db.Turmas
                             where t.Sala.SalaId == this.SalaId
                             select t;

                var turma_alunos = from t in turmas
                                   join ta in db.TurmaAlunos on t.TurmaId equals ta.Turma.TurmaId
                                   select ta;

                var number_alunos = (from ta in turma_alunos
                             join a in db.Alunos on ta.Aluno.AlunoId equals a.AlunoId
                             select a).Count();

                return number_alunos;
            }
        }

    }
}