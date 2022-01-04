package br.edu.ifrn.tcc.controladores;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.tcc.dominio.Arquivo;
import br.edu.ifrn.tcc.dominio.Foto;
import br.edu.ifrn.tcc.dominio.Usuario;
import br.edu.ifrn.tcc.repository.FotoRepository;
import br.edu.ifrn.tcc.repository.UsuarioRepository;
import br.edu.ifrn.tcc.uteis.CompressaoImagemUtils;

@Controller
@RequestMapping("/uploadFoto")
public class UploadFotoController {
    
    @Autowired
    private FotoRepository fotoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/publicar")
    public String PublicarFoto(ModelMap model) {
        //bucando dados do usuario logado
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).get();
        
        model.addAttribute("usuario", usuario);
        
        return "usuario/uploadFoto";
    }
    
    @PostMapping("/salvarUploadFoto")
    @Transactional(readOnly = false)
    public String salvarUploadFoto(Usuario usuario, @RequestParam("file") MultipartFile[] fotos,
            @RequestParam("legenda") String[] legendas, RedirectAttributes attr) {
    	
    	Usuario usuarioBanco = usuarioRepository.findById(usuario.getId()).get();

        try {
            
            for (int i = 0; i < fotos.length; i++) {
                // Pegando a foto da posicao i do array
                MultipartFile arquivoFoto = fotos[i];
                
                if ( fotos[0].isEmpty() 
                        &&  fotos[1].isEmpty()
                        &&  fotos[2].isEmpty()
                        &&  fotos[3].isEmpty()) {

                    attr.addFlashAttribute("msgErro", "Adicione no minímo uma foto");
                    return "redirect:/uploadFoto/publicar";
                    
                }
                
                // Se nao for um arquivo um vazio
                if (arquivoFoto != null && !arquivoFoto.isEmpty()) {

                    // normalizando o nome da foto
                    String nomeFoto = StringUtils.cleanPath(arquivoFoto.getOriginalFilename());
                     
                    
                    //Descobrindo a extensão do arquivo
                    int tam = nomeFoto.length();
                    String extensao = nomeFoto.substring(tam - 3);
                                       
                    //Comprimindo a imagem
                    byte[] bytes = CompressaoImagemUtils.resize(arquivoFoto.getInputStream(), extensao);

                    Arquivo arquivoFotoBD = new Arquivo(null, nomeFoto, arquivoFoto.getContentType(),
                            bytes);

                    // Criando um novo objeto do tipo Foto para ser salvo no banco
                    Foto f = new Foto();
                    f.setArquivo(arquivoFotoBD);


                    // Adicionando legenda na foto caso tenha sido escrita
                    // se existir legenda na posicao i
                    if (legendas[i] != null) {
                        // adicionar a legenda na foto
                        f.setLegenda(legendas[i]);
                    }

                    // Verificando se o usuario ja havia enviado anteriormente outras fotos
                    // Se nao tiver enviado
                    if (usuarioBanco.getPubliFoto() == null || usuarioBanco.getPubliFoto().isEmpty()) {
                        // Entao cria uma nova lista de fotos (vazia)
                        usuarioBanco.setPubliFoto(new ArrayList<>());
                    }

                    // Adicionando a foto que acabou de ser criada a  lista de fotos do usuario
                    usuarioBanco.getPubliFoto().add(f);
                }
            }
            
            if (usuarioBanco.getFoto() != null && usuarioBanco.getFoto().getId() == null)
                usuarioBanco.setFoto(null);
            
            // salvando as fotos no banco
            fotoRepository.saveAll(usuarioBanco.getPubliFoto());
            usuarioRepository.save(usuarioBanco);
            
            attr.addFlashAttribute("msgSucesso", "foto adicionada com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadFoto/publicar";
    }   
    }