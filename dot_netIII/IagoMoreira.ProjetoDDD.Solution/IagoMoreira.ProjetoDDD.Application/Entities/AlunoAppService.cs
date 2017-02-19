using IagoMoreira.ProjetoDDD.Application.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IagoMoreira.ProjetoDDD.Application.ViewModel;
using AutoMapper;
using IagoMoreira.ProjetoDDD.Domain;
using IagoMoreira.ProjetoDDD.Domain.Interfaces.Services;

namespace IagoMoreira.ProjetoDDD.Application.Entities
{
    public class AlunoAppService : AppServiceBase, IAlunoAppService
    {
        private readonly Domain.Interfaces.Services.IAlunoService _alunoService;

        public AlunoAppService(Domain.Interfaces.Services.IAlunoService service)
        {
            _alunoService = service;
        }

        public void AdicionarAluno(AlunoViewModel alunoViewModel)
        {
            var aluno = Mapper.Map<AlunoViewModel, Aluno>(alunoViewModel);
            BeginTransaction();
            _alunoService.AdicionarAluno(aluno);
            Commit();
        }

        public void DeletarAluno(AlunoViewModel alunoViewModel)
        {
            var aluno = Mapper.Map<AlunoViewModel, Aluno>(alunoViewModel);
            BeginTransaction();
            aluno = _alunoService.ObterAlunoPorId(aluno.AlunoId);
            _alunoService.DeletarAluno(aluno);
            Commit();
        }

        public void EditarAluno(AlunoViewModel alunoViewModel)
        {
            var aluno = Mapper.Map<AlunoViewModel, Aluno>(alunoViewModel);
            BeginTransaction();
            _alunoService.EditarAluno(aluno);
            Commit();
        }

        public AlunoViewModel ObterAlunoPorId(int id)
        {
            var result = _alunoService.ObterAlunoPorId(id);
            var aluno = Mapper.Map<Aluno, AlunoViewModel>(result);
            return aluno;
        }

        public IEnumerable<AlunoViewModel> ObterAlunos()
        {
            var result = _alunoService.ObterAlunos();
            var list = Mapper.Map<IEnumerable<Aluno>, IEnumerable<AlunoViewModel>> (result);
            return list;
        }
    }
}
