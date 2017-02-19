using AutoMapper;
using IagoMoreira.ProjetoDDD.Application.ViewModel;
using IagoMoreira.ProjetoDDD.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Application.AutoMapper
{
    public class DomainToViewModelMappingProfile : Profile
    {
        protected override void Configure()
        {
            CreateMap<Aluno, AlunoViewModel>();
        }
    }
}
