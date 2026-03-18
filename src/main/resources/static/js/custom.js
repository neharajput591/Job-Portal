// Aos Initialization 
AOS.init({duration:1000, once:true});

// menu toggle
function toggleMenu(){
    const nav = document.getElementById('nav');
    nav.classList.toggle('active');
    
}

// video modal

function openModal(){
    document.getElementById('videoModal').classList.add('active');
    const video = document.getElementById('modalVideo');
    video.currentTime = 0;
    video.play();
}

function closeModal(event){
    if(event.target.id === 'videoModal' || event.target.className === 'close-btn'){
        const modal = document.getElementById('videoModal');
        modal.classList.remove('active');
        const video = document.getElementById('modalVideo');
        video.pause();
    }
}

// counter section

const counters = document.querySelectorAll('.counter-number');
const speed = 200;
counters.forEach(counter => {
    const animate = () =>{
        const target = + counter.getAttribute('data-target');
        const count = +counter.innerText;
        const increment = target / speed;
        if(count <target){
            counter.innerText = Math.ceil(count + increment);
            setTimeout(animate,1);
        }
        else{
            counter.innerText = target + " +";
        }
    }
    animate();
})

// scroll events

window.onscroll = () =>{
    document.getElementById("mainHeader").classList.toggle("fixed", scrollY >100);
    document.getElementById("backToTop").style.display = scrollY > 300 ? "block" : "none";

};

// back to top smooth scroll
document.getElementById("backToTop").onclick = () =>{
    window.scrollTo({top:0,behavior:"smooth"});
}

